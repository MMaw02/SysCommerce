package pe.mmaw.projectventafactura.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.mmaw.projectventafactura.model.dao.IProductoDAO;
import pe.mmaw.projectventafactura.model.entity.Producto;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private IProductoDAO productoDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return (List<Producto>) productoDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findProductById(Integer id) {
        return productoDAO.findById(id).orElse(null);
    }

    @Override
    public Producto saveProduct(Producto producto) {

        if (producto.getId() != null) {
            Producto product = productoDAO.findById(producto.getId()).get();

            producto.setUpdatedAt(LocalDateTime.now());
            producto.setCreatedAt(product.getCreatedAt());
        }

        return productoDAO.save(producto);
    }

    @Override
    public void deleteProduct(Integer id) {
        productoDAO.deleteById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Producto> findByNombre(String term) {
        return productoDAO.findByNombreLikeIgnoreCase("%"+term+"%");
    }
}
