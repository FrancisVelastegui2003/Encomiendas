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
    public Factura obtenerFacturaPorId(Integer id) throws SQLException {
        return facturaService.getFacturaById(id);
    }

    // Generar una nueva factura
    public void generarFactura(Factura factura) throws SQLException {
        facturaService.saveFactura(factura);
    }

    // Eliminar una factura (no implementado)
    public void eliminarFactura(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Eliminación de facturas no soportada.");
    }

    // Actualizar una factura existente (no implementado)
    public void actualizarFactura(Integer id, Factura factura) throws SQLException {
        throw new UnsupportedOperationException("Actualización de facturas no soportada.");
    }

    // Nueva lógica para manejar la generación de facturas desde la vista
    public void generarFacturaDesdeVista(Encomienda encomienda, Usuario cliente) throws SQLException {
        // Crear la factura utilizando los datos proporcionados
        Factura factura = Factura.crearFactura(encomienda, cliente);
        // Guardar la factura en la base de datos
        generarFactura(factura);
    }
}
