package pe.mmaw.projectventafactura.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pe.mmaw.projectventafactura.model.entity.Factura;

public interface IFacturaDAO extends CrudRepository<Factura, Integer> {

    @Query("select f from Factura f join fetch f.usuario c join fetch f.items l join fetch l.producto where f.id=?1")
    public Factura fetchByIdWithClienteWithItemFacturaWithProducto(Integer id);
}
