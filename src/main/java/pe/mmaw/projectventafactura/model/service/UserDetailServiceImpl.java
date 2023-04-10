package pe.mmaw.projectventafactura.model.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.mmaw.projectventafactura.model.entity.Role;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private LoginService loginService;

    private final Integer USER_NOT_FOUND = 0;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Integer idUser = loginService.getUserId(username);

        if (idUser != USER_NOT_FOUND) {
            Role user = loginService.getUser(username);
            httpSession.setAttribute("iduser", user.getId());

            return User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRol().name())
                    .build();
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }
}
