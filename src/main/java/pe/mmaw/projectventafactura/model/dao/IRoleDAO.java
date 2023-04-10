package pe.mmaw.projectventafactura.model.dao;

import org.springframework.data.repository.CrudRepository;
import pe.mmaw.projectventafactura.model.entity.Role;

public interface IRoleDAO extends CrudRepository<Role, Integer> {

    public Role findByUsername(String username);
}
