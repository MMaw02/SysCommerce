package pe.mmaw.projectventafactura.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pe.mmaw.projectventafactura.model.entity.Producto;
import pe.mmaw.projectventafactura.model.entity.Usuario;

import java.util.List;

public interface IUsuarioDAO extends CrudRepository<Usuario, Integer> {

    @Query("select u from Usuario u left join fetch u.role r where u.id = ?1")
    public Usuario fetchByIdWithRole(Integer id);

    @Query("SELECT u FROM Usuario u JOIN FETCH u.role WHERE u.id = ?1")
    public Usuario findByIdWithrole(Integer id);

    public List<Usuario> findByDniLikeIgnoreCase(String term);

    public Usuario findByDni(String dni);
}
