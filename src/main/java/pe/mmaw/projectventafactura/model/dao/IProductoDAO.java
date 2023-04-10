package pe.mmaw.projectventafactura.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pe.mmaw.projectventafactura.model.entity.Producto;

import java.util.List;

public interface IProductoDAO extends CrudRepository<Producto, Integer> {

    public List<Producto> findByNombreLikeIgnoreCase(String term);
}
