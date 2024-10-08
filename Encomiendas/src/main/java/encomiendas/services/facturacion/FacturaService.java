package encomiendas.services.facturacion;

import encomiendas.model.data.facturacion.FacturaRepository;
import encomiendas.model.entity.facturacion.Factura;

import java.sql.SQLException;
import java.util.List;

public class FacturaService {

    private final FacturaRepository facturaRepository;

    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    // Obtener todas las facturas
    public List<Factura> getAllFacturas() throws SQLException {
        return facturaRepository.findAll();
    }

    // Obtener una factura por su ID
    public Factura getFacturaById(Integer id) throws SQLException {
        return facturaRepository.getById(id);
    }

    // Guardar una nueva factura
    public void saveFactura(Factura factura) throws SQLException {
        facturaRepository.save(factura);
    }

    // Eliminar una factura (no implementado)
    public void deleteFactura(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Eliminación de facturas no soportada.");
    }

    // Actualizar una factura (no implementado)
    public void updateFactura(Integer id, Factura factura) throws SQLException {
        throw new UnsupportedOperationException("Actualización de facturas no soportada.");
    }
}
