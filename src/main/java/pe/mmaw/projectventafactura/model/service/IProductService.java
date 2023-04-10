package pe.mmaw.projectventafactura.model.service;

import pe.mmaw.projectventafactura.model.entity.Producto;

import java.util.List;

public interface IProductService {

    List<Producto> findAll();
    Producto findProductById(Integer id);
    Producto saveProduct(Producto producto);
    void deleteProduct(Integer id);
    List<Producto> findByNombre(String term);
}
