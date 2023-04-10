package pe.mmaw.projectventafactura.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pe.mmaw.projectventafactura.model.entity.Role;
import pe.mmaw.projectventafactura.model.service.LoginService;

@Controller
public class AppController {

    @Autowired
    private LoginService loginService;

    @GetMapping({"/home", "/"})
    public String home(HttpSession httpSession, Model model) {

        Role user = loginService.getUser(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        try {
            model.addAttribute("user", user);
        } catch (Exception e) {

        }

        return "index";
    }
}
