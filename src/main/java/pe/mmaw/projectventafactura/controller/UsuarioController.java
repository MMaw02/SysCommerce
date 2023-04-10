package pe.mmaw.projectventafactura.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.model.IModel;
import pe.mmaw.projectventafactura.model.entity.Role;
import pe.mmaw.projectventafactura.model.entity.Usuario;
import pe.mmaw.projectventafactura.model.service.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private UpdateFile updateFile;

    @Autowired
    private LoginService loginService;

    @GetMapping("/list")
    public String listUsuario(HttpSession httpSession, Model model) {
        List<Usuario> usuarios = usuarioService.findAll();

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("titulo", "Listas de Usuarios");

        Role user = loginService.getUser(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        try {
            model.addAttribute("user", user);
        } catch (Exception e) {

        }
        return "/usuario/listar";
    }

    @GetMapping("/form")
    public String formUsuario(HttpSession httpSession, Model model) {
        Usuario usuario = new Usuario();

        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Formulario de Usuarios");

        Role user = loginService.getUser(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        try {
            model.addAttribute("user", user);
        } catch (Exception e) {

        }
        return "/usuario/form";
    }

    @PostMapping("/save")
    public String saveUsuario(Usuario usuario, @RequestParam("file") MultipartFile multipartFile) throws IOException {

        if (usuario.getId() == null) {
            usuario.setFoto(updateFile.update(multipartFile));
        }
        else {
//            Este es el usuario de la BD
            Usuario user = usuarioService.findUsuarioById(usuario.getId());
            if (multipartFile.isEmpty()) {
//                SI CUANDO ESTA CREANDO O EDITANDO NO ENVIA NINGUN IMAGEN
                if (user.getFoto() == null) {
                    usuario.setFoto(updateFile.update(multipartFile));
                } else {
                    usuario.setFoto(user.getFoto());
                }
            } else {
                if (!user.getFoto().equals("default.jpg")) {
//                    SI EN EL CASO INGRESA UNA IMAGEN, PERO NO ES LA MIS DEL DEFAULT.
                    updateFile.delete(user.getFoto());
                }
                usuario.setFoto(updateFile.update(multipartFile));
            }
        }

        usuarioService.saveUsuario(usuario);
        return "redirect:/usuario/list";
    }

    @GetMapping("/form/{id}")
    public String editUsuario(@PathVariable Integer id, Model model, HttpSession httpSession) {
        Usuario usuario = usuarioService.findUsuarioById(id);

        model.addAttribute("usuario", usuario);

        Role user = loginService.getUser(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        try {
            model.addAttribute("user", user);
        } catch (Exception e) {

        }

        return "/usuario/form-edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteUsuario(@PathVariable Integer id) {

        Usuario user = usuarioService.findUsuarioById(id);
        String userImage = user.getFoto();

        if (!userImage.equals("default.jpg")) {
            updateFile.delete(userImage);
        }

        usuarioService.deleteUsuario(id);

        return "redirect:/usuario/list";
    }

    @GetMapping("/profile/{id}")
    public String profileUsuario(@PathVariable Integer id, Model model, HttpSession httpSession) {
        Usuario user = usuarioService.findUsuarioById(id);

        model.addAttribute("usuario", user);

        Role iuser = loginService.getUser(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        try {
            model.addAttribute("user", iuser);
        } catch (Exception e) {

        }
        return "/usuario/profile";
    }
}
