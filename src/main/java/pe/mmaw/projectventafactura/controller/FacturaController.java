package pe.mmaw.projectventafactura.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.mmaw.projectventafactura.model.dao.IUsuarioDAO;
import pe.mmaw.projectventafactura.model.entity.*;
import pe.mmaw.projectventafactura.model.service.IFacturaService;
import pe.mmaw.projectventafactura.model.service.IProductService;
import pe.mmaw.projectventafactura.model.service.IUsuarioService;
import pe.mmaw.projectventafactura.model.service.LoginService;

import java.util.List;

@Controller
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IFacturaService facturaService;

    @Autowired
    private LoginService loginService;

    @GetMapping("/list")
    public String listFactura(Model model, HttpSession httpSession) {
        List<Factura> facturas = facturaService.findAll();

        model.addAttribute("facturas", facturas );
        model.addAttribute("titulo", "Lista de Facturas");

        Role user = loginService.getUser(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        try {
            model.addAttribute("user", user);
        } catch (Exception e) {

        }

        return "/factura/listar";
    }

    @GetMapping("/form")
    public String formFactura(Factura factura, Model model, HttpSession httpSession) {

        model.addAttribute("titulo", "Formulario de Facturas");

        Role user = loginService.getUser(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        try {
            model.addAttribute("user", user);
        } catch (Exception e) {

        }
        return "/factura/form";
    }

    @PostMapping("/save")
    public String saveFactura(Factura factura,
                              @RequestParam(name = "buscar_usuario", required = false) String dni,
                              @RequestParam(name ="item_id[]", required = false) Integer[] itemId,
                              @RequestParam(name ="cantidad[]", required = false) Integer[] cantidad,
                              Model model
    ) {
        Usuario user = usuarioService.findByDni(dni);
        factura.setUsuario(user);

        for (int i = 0; i < itemId.length; i++) {
            Producto producto = productService.findProductById(itemId[i]);

            FacturasItems linea = new FacturasItems();
            linea.setCantidad(cantidad[i]);
            linea.setProducto(producto);

            factura.addItemFactura(linea);
        }

        facturaService.saveFactura(factura);

        return "redirect:/factura/list";
    }

    @GetMapping("/ver/{id}")
    public String detailFactura(@PathVariable Integer id, HttpSession httpSession, Model model) {
        Factura factura = facturaService.fetchByIdWithClienteWithItemFacturaWithProducto(id);

        model.addAttribute("factura", factura);

        Role user = loginService.getUser(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        try {
            model.addAttribute("user", user);
        } catch (Exception e) {

        }
        return "factura/ver";
    }

    @GetMapping(value = "/cargar-usuarios/{term}", produces = {"application/json"})
    public @ResponseBody List<Usuario> cargaUsuarios(@PathVariable String term) {
        return usuarioService.findByDniList(term);
    }

    @GetMapping(value = "/cargar-productos/{term}", produces = {"application/json"})
    public @ResponseBody List<Producto> cargaProductos(@PathVariable String term) {
        return productService.findByNombre(term);
    }
}
