package pe.mmaw.projectventafactura.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.mmaw.projectventafactura.model.entity.Role;

@Service
public class LoginService {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private RoleServiceImpl roleService;

    public Boolean existUser(String username) {
        try {
            roleService.findByUsername(username);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Integer getUserId(String username) {
        try {
            return roleService.findByUsername(username).getId();
        } catch (Exception e) {
            return 0;
        }
    }

    public Role.Rol getUserType(String username) {
        return roleService.findByUsername(username).getRol();
    }

    public Role getUser(String username) {
        try {
            return roleService.findByUsername(username);
        } catch (Exception e) {
            return new Role();
        }
    }

    public Role getUser(Integer id) {
        try {
            return roleService.findById(id);
        } catch (Exception e) {
            return new Role();
        }
    }

}
