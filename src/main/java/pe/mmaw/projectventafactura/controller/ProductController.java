package pe.mmaw.projectventafactura.controller;

import jakarta.persistence.Entity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.mmaw.projectventafactura.model.dao.IProductoDAO;
import pe.mmaw.projectventafactura.model.entity.Producto;
import pe.mmaw.projectventafactura.model.entity.Role;
import pe.mmaw.projectventafactura.model.entity.Usuario;
import pe.mmaw.projectventafactura.model.service.IProductService;
import pe.mmaw.projectventafactura.model.service.LoginService;
import pe.mmaw.projectventafactura.model.service.ProductServiceImpl;
import pe.mmaw.projectventafactura.model.service.UpdateFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/producto")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private UpdateFile updateFile;

    @Autowired
    private LoginService loginService;

    @GetMapping("/list")
    public String listProduct(Model model, HttpSession httpSession) {

        List<Producto> productos = productService.findAll();

        model.addAttribute("productos", productos);
        model.addAttribute("titulo", "Lista de Producto");

        Role user = loginService.getUser(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        try {
            model.addAttribute("user", user);
        } catch (Exception e) {

        }

        return "/producto/listar";
    }

    @GetMapping("/form")
    public String formProduct(Producto producto, Model model, HttpSession  httpSession) {
        model.addAttribute("titulo", "Formulario Producto");

        Role user = loginService.getUser(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        try {
            model.addAttribute("user", user);
        } catch (Exception e) {

        }

        return "/producto/form";
    }

    @PostMapping("/save")
    public String saveProduct(Producto producto, @RequestParam("file") MultipartFile multipartFile) throws IOException {

        if (producto.getId() == null) {
            producto.setFoto(updateFile.update(multipartFile));
        } else {
            Producto product = productService.findProductById(producto.getId());
            producto.setUpdatedAt(LocalDateTime.now());
            producto.setCreatedAt(product.getCreatedAt());
            if (multipartFile.isEmpty()) {
                if (product.getFoto() == null) {
                    producto.setFoto(updateFile.update(multipartFile));
                } else {
                    producto.setFoto(producto.getFoto());
                }
            } else {
                if (!product.getFoto().equals("default.jpg")) {
                    updateFile.delete(producto.getFoto());
                }
                producto.setFoto(updateFile.update(multipartFile));
            }
        }

        productService.saveProduct(producto);
        return "redirect:/producto/list";
    }

    @GetMapping("/form/{id}")
    public String editProduct(@PathVariable Integer id, Model model, HttpSession httpSession) {
        Producto producto = productService.findProductById(id);

        model.addAttribute("producto", producto);

        Role user = loginService.getUser(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        try {
            model.addAttribute("user", user);
        } catch (Exception e) {

        }

        return "/producto/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {

        Producto producto = productService.findProductById(id);
        String productImage = producto.getFoto();

        if (!productImage.equals("default.jpg")) {
            updateFile.delete(productImage);
        }

        productService.deleteProduct(id);
        return "redirect:/producto/list";
    }

    @GetMapping("/ver/{id}")
    public String viewProduct(@PathVariable Integer id, Model model, HttpSession httpSession) {

        Producto producto = productService.findProductById(id);

        model.addAttribute("producto", producto);
        model.addAttribute("titulo", "Producto");

        Role user = loginService.getUser(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        try {
            model.addAttribute("user", user);
        } catch (Exception e) {

        }

        return "/producto/ver";
    }
}
