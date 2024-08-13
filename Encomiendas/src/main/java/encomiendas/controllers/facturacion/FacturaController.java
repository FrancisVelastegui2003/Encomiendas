package encomiendas.controllers.facturacion;

import encomiendas.model.entity.facturacion.Factura;
import encomiendas.model.entity.encomiendas.Encomienda;
import encomiendas.model.entity.usuarios.Usuario;
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
    public Factura obtenerFacturaPorId(String id) throws SQLException {
        return facturaService.getFacturaById(id);
    }

    // Generar una nueva factura
    public void generarFactura(Factura factura) throws SQLException {
        facturaService.saveFactura(factura);
    }

    // Nueva lógica para manejar la generación de facturas desde la vista
    public Factura generarFacturaDesdeVista(Encomienda encomienda, Usuario cliente) throws SQLException {
        Factura factura = Factura.crearFactura(encomienda, cliente);
        generarFactura(factura);
        return factura;
    }
}
