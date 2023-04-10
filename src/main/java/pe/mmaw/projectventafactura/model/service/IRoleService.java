package pe.mmaw.projectventafactura.model.service;

import org.springframework.data.repository.CrudRepository;
import pe.mmaw.projectventafactura.model.entity.Role;

public interface IRoleService {
    Role findByUsername(String Username);

    Role findById(Integer id);
}
