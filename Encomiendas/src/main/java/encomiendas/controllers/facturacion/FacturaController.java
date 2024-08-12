package encomiendas.controllers.facturacion;

import encomiendas.model.entity.facturacion.Factura;
import encomiendas.services.facturacion.FacturaService;

import java.sql.SQLException;
import java.util.List;

public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    // Listar todas las facturas
    public List<Factura> listarFacturas() throws SQLException {
        return facturaService.getAllFacturas();
    }

    // Obtener una factura por ID
    public Factura obtenerFacturaPorId(Integer id) throws SQLException {
        return facturaService.getFacturaById(id);
    }

    // Generar una nueva factura
    public void generarFactura(Factura factura) throws SQLException {
        facturaService.saveFactura(factura);
    }

    // Eliminar una factura
    public void eliminarFactura(Integer id) throws SQLException {
        facturaService.deleteFactura(id);
    }

    // Actualizar una factura existente
    public void actualizarFactura(Integer id, Factura factura) throws SQLException {
        facturaService.updateFactura(id, factura);
    }
    
    // CREEADO POR DEFECTO (MEJORAR LA LÓGICA)
    public void generarFactura(encomiendas.views.facturacion.Factura factura) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
