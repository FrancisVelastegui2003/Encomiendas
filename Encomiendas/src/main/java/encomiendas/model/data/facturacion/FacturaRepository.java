package encomiendas.model.data.facturacion;

import encomiendas.model.data.Repository;
import encomiendas.model.data.encomiendas.EncomiendaRepository;
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
        String sql = "INSERT INTO factura (fecha, impuestos, descuentos, total, id_encomienda) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement myStatement = myConn.prepareStatement(sql)) {
            myStatement.setDate(1, java.sql.Date.valueOf(factura.getFecha()));
            myStatement.setDouble(2, factura.getImpuestos());
            myStatement.setDouble(3, factura.getDescuentos());
            myStatement.setDouble(4, factura.getTotal());
            //myStatement.setInt(5, factura.getEncomienda().getIdEncomienda());
            myStatement.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM factura WHERE id_factura = ?";
        try (PreparedStatement myStatement = myConn.prepareStatement(sql)) {
            myStatement.setInt(1, id);
            myStatement.executeUpdate();
        }
    }

    @Override
    public void update(Integer id, Factura factura) throws SQLException {
        String sql = "UPDATE factura SET fecha = ?, impuestos = ?, descuentos = ?, total = ? WHERE id_factura = ?";
        try (PreparedStatement myStatement = myConn.prepareStatement(sql)) {
            myStatement.setDate(1, java.sql.Date.valueOf(factura.getFecha()));
            myStatement.setDouble(2, factura.getImpuestos());
            myStatement.setDouble(3, factura.getDescuentos());
            myStatement.setDouble(4, factura.getTotal());
            myStatement.setInt(5, id);
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
        
        
        // COMPLETAR PARA EL FUNIONAMIENTO FINAL:
        Encomienda encomienda = obtenerEncomienda(myRs.getInt("id_encomienda"));
        factura.setEncomienda(encomienda);
        return factura;
    }

    // Método para obtener una Encomienda relacionada a una factura
    // MEJORAR 
    private Encomienda obtenerEncomienda(int idEncomienda) throws SQLException {
        EncomiendaRepository encomiendaRepository = new EncomiendaRepository(myConn);
        return encomiendaRepository.getById(idEncomienda);
    }
}
