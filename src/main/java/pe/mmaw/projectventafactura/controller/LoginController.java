package pe.mmaw.projectventafactura.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.mmaw.projectventafactura.model.entity.Role;
import pe.mmaw.projectventafactura.model.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public String login() {
        return "login";
    }

    @GetMapping("/access")
    public String access(HttpSession httpSession) {
        Role user = loginService.getUser(Integer.parseInt(httpSession.getAttribute("iduser").toString()));

        if (loginService.existUser(user.getUsername())) {
            if (loginService.getUserType(user.getUsername()).name().equals("ADMIN")) {
                return "redirect:/usuario/form";
            } else {
                return "redirect:/usuario/lista";
            }
        }

        return "redirect:/usuario/lista";
    }
}
