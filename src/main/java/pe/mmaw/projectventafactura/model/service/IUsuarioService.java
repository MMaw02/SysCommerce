package pe.mmaw.projectventafactura.model.service;

import pe.mmaw.projectventafactura.model.entity.Producto;
import pe.mmaw.projectventafactura.model.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    List<Usuario> findAll();
    Usuario findUsuarioById(Integer id);
    Usuario saveUsuario(Usuario usuario);
    void deleteUsuario(Integer id);

    Usuario fetchByIdWithRole(Integer id);


    List<Usuario> findByDniList(String term);

    Usuario findByDni(String dni);
}
