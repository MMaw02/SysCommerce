package pe.mmaw.projectventafactura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.mmaw.projectventafactura.model.entity.Usuario;
import pe.mmaw.projectventafactura.model.service.RegistrationService;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping
    public String register(Usuario usuario) {

        return "register";
    }

    @PostMapping
    public String registerUser(Usuario usuario) {

        registrationService.register(usuario);
        return "redirect:/register";
    }

}
