package pe.mmaw.projectventafactura.model.service;

import pe.mmaw.projectventafactura.model.entity.Factura;

import java.util.List;

public interface IFacturaService {

    List<Factura> findAll();

    Factura saveFactura(Factura factura);

    Factura findFacturaById(Integer id);

    void deleteFactura(Integer id);

    Factura fetchByIdWithClienteWithItemFacturaWithProducto(Integer id);

}
