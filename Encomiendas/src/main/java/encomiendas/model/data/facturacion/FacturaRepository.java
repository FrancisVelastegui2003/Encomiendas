package encomiendas.model.data.facturacion;

import encomiendas.model.data.Repository;
import encomiendas.model.entity.facturacion.Factura;
import encomiendas.model.entity.encomiendas.Encomienda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaRepository implements Repository<Factura> {

    private Connection myConn;

    public FacturaRepository(Connection myConn) {
        this.myConn = myConn;
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

    @Override
    public Factura getById(Integer id) throws SQLException {
        Factura factura = null;
        String sql = "SELECT * FROM factura WHERE id_factura = ?";
        try (PreparedStatement myStatement = myConn.prepareStatement(sql)) {
            myStatement.setInt(1, id);
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
            myStatement.setString(1, factura.getIdFactura().toString());
            myStatement.setDate(2, java.sql.Date.valueOf(factura.getFecha()));
            myStatement.setDouble(3, factura.getImpuestos());
            myStatement.setDouble(4, factura.getDescuentos());
            myStatement.setDouble(5, factura.getTotal());
           // myStatement.setInt(6, factura.getEncomienda().getIdEncomienda());
            myStatement.setInt(7, factura.getCedula_cliente());
            myStatement.setBoolean(8, factura.isEstado_factura());
            myStatement.executeUpdate();
        }
    }

    // Método para crear una Factura a partir del ResultSet de la base de datos
    private Factura createFactura(ResultSet myRs) throws SQLException {
        Factura factura = new Factura();
        factura.setIdFactura(myRs.getInt("id_factura"));
        factura.setFecha(myRs.getDate("fecha").toLocalDate());
        factura.setImpuestos(myRs.getDouble("impuestos"));
        factura.setDescuentos(myRs.getDouble("descuentos"));
        factura.setTotal(myRs.getDouble("total"));

        // Obtener la encomienda relacionada
        Encomienda encomienda = obtenerEncomienda(myRs.getInt("id_encomienda"));
        factura.setEncomienda(encomienda);

        factura.setCedula_cliente(myRs.getInt("cedula_cliente"));
        factura.setEstado_factura(myRs.getBoolean("estado_factura"));

        return factura;
    }

    // Método para obtener una Encomienda relacionada a una factura
    private Encomienda obtenerEncomienda(int idEncomienda) throws SQLException {
        String sql = "SELECT * FROM encomienda WHERE id_encomienda = ?";
        Encomienda encomienda = null;

        try (PreparedStatement myStatement = myConn.prepareStatement(sql)) {
            myStatement.setInt(1, idEncomienda);
            ResultSet myRs = myStatement.executeQuery();

            if (myRs.next()) {
               // encomienda = new Encomienda();
               // encomienda.setIdEncomienda(myRs.getInt("id_encomienda"));
               // encomienda.setPrecioEncomienda(myRs.getFloat("precio_encomienda"));
            }
        }
        return encomienda;
    }

    @Override
    public void delete(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Integer id, Factura t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
