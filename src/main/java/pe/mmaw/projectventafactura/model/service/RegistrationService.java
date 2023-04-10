package pe.mmaw.projectventafactura.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.mmaw.projectventafactura.model.entity.Usuario;

@Service
public class RegistrationService {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(Usuario usuario) {
        usuario.getRole().setPassword(passwordEncoder.encode(usuario.getRole().getPassword()));

        usuarioService.saveUsuario(usuario);
    }
}
