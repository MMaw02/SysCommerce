package pe.mmaw.projectventafactura.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.mmaw.projectventafactura.model.dao.IFacturaDAO;
import pe.mmaw.projectventafactura.model.entity.Factura;

import java.util.List;

@Service
public class FacturaServiceImpl implements  IFacturaService{

    @Autowired
    private IFacturaDAO facturaDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Factura> findAll() {
        return (List<Factura>) facturaDAO.findAll();
    }

    @Override
    public Factura saveFactura(Factura factura) {
        return facturaDAO.save(factura);
    }

    @Override
    @Transactional(readOnly = true)
    public Factura findFacturaById(Integer id) {
        return facturaDAO.findById(id).orElse(null);
    }

    @Override
    public void deleteFactura(Integer id) {
        facturaDAO.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Factura fetchByIdWithClienteWithItemFacturaWithProducto(Integer id) {
        return facturaDAO.fetchByIdWithClienteWithItemFacturaWithProducto(id);
    }
}
