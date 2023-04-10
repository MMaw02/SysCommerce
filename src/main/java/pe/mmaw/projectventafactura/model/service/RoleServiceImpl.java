package pe.mmaw.projectventafactura.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.mmaw.projectventafactura.model.dao.IRoleDAO;
import pe.mmaw.projectventafactura.model.entity.Role;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDAO roleDAO;

    @Override
    public Role findByUsername(String username) {
        return roleDAO.findByUsername(username);
    }

    @Override
    public Role findById(Integer id) {
        return roleDAO.findById(id).get();
    }
}
