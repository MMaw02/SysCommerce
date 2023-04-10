package pe.mmaw.projectventafactura.model.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class LogoutService {

    public void logout(HttpSession httpSession) {
        httpSession.removeAttribute("iduser");
    }
}
