package pe.mmaw.projectventafactura.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.mmaw.projectventafactura.model.service.LogoutService;

@Controller
@RequestMapping("/close")
public class LogoutController {

    @Autowired
    private LogoutService logoutService;

    @GetMapping
    public String logout(HttpSession httpSession) {
        logoutService.logout(httpSession);
        return "redirect:/home";
    }
}
