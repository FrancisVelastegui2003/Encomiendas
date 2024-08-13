package encomiendas.model.data.facturacion;

import encomiendas.model.data.Repository;
import encomiendas.model.entity.facturacion.Factura;
import encomiendas.model.entity.encomiendas.Encomienda;
import encomiendas.model.data.encomiendas.EncomiendaRepository;
import encomiendas.model.entity.usuarios.Usuario;
import encomiendas.model.data.usuarios.ClienteRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaRepository implements Repository<Factura> {

    private Connection myConn;
    private EncomiendaRepository encomiendaRepository;
    private ClienteRepository clienteRepository;

    public FacturaRepository(Connection myConn) {
        this.myConn = myConn;
        this.encomiendaRepository = new EncomiendaRepository(myConn);
        this.clienteRepository = new ClienteRepository(myConn);
    }

    @Override
    public List<Factura> findAll() throws SQLException {
        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT * FROM factura";
        try (Statement myStatement = myConn.createStatement();
             ResultSet myRs = myStatement.executeQuery(sql)) {
            while (myRs.next()) {
                Factura factura = createFactura(myRs);
                facturas.add(factura);
            }
        }
        return facturas;
    }

    public Factura getById(String id) throws SQLException {
        Factura factura = null;
        String sql = "SELECT * FROM factura WHERE id_factura = ?";
        try (PreparedStatement myStatement = myConn.prepareStatement(sql)) {
            myStatement.setString(1, id);
            ResultSet myRs = myStatement.executeQuery();
            if (myRs.next()) {
                factura = createFactura(myRs);
            }
        }
        return factura;
    }

    @Override
    public void save(Factura factura) throws SQLException {
        String sql = "INSERT INTO factura (id_factura, fecha, impuestos, descuentos, total, id_encomienda, cedula_cliente, estado_factura) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement myStatement = myConn.prepareStatement(sql)) {
            myStatement.setString(1, factura.getIdFactura());
            myStatement.setDate(2, java.sql.Date.valueOf(factura.getFecha()));
            myStatement.setDouble(3, factura.getImpuestos());
            myStatement.setDouble(4, factura.getDescuentos());
            myStatement.setDouble(5, factura.getTotal());
            myStatement.setInt(6, factura.getEncomienda().getIdEncomienda());
            myStatement.setString(7, factura.getCedula_cliente());
            myStatement.setBoolean(8, factura.isEstado_factura());
            myStatement.executeUpdate();
        }
    }

    // Método para crear una Factura a partir del ResultSet de la base de datos
    private Factura createFactura(ResultSet myRs) throws SQLException {
        Factura factura = new Factura();
        factura.setIdFactura(myRs.getString("id_factura"));
        factura.setFecha(myRs.getDate("fecha").toLocalDate());
        factura.setImpuestos(myRs.getDouble("impuestos"));
        factura.setDescuentos(myRs.getDouble("descuentos"));
        factura.setTotal(myRs.getDouble("total"));

        // Obtener la encomienda relacionada
        Encomienda encomienda = encomiendaRepository.getById(myRs.getInt("id_encomienda"));
        factura.setEncomienda(encomienda);

        // Obtener el cliente relacionado
        Usuario cliente = clienteRepository.getById(myRs.getString("cedula_cliente"));
        factura.setCedula_cliente(cliente.getCedula());

        factura.setEstado_factura(myRs.getBoolean("estado_factura"));

        return factura;
    }

    @Override
    public void delete(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Integer id, Factura t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Factura getById(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
