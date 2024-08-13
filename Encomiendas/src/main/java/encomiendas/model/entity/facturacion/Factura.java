package encomiendas.model.entity.facturacion;

import encomiendas.model.entity.encomiendas.Encomienda;
import encomiendas.model.entity.usuarios.Usuario;
import java.time.LocalDate;
import java.util.UUID;

public class Factura {
    private String idFactura;
    private LocalDate fecha;
    private Double impuestos;
    private Double descuentos;
    private Double total;
    private Encomienda encomienda; 
    private String cedula_cliente;
    private boolean estado_factura;
    
    public Factura() {}

    public Factura(String idFactura, LocalDate fecha, Double impuestos, Double descuentos, Double total, Encomienda encomienda, String cedula_cliente, boolean estado_factura) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.impuestos = impuestos;
        this.descuentos = descuentos;
        this.total = total;
        this.encomienda = encomienda;
        this.cedula_cliente = cedula_cliente;
        this.estado_factura = estado_factura;
    }

    // Getters y Setters

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(Double impuestos) {
        this.impuestos = impuestos;
    }

    public Double getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(Double descuentos) {
        this.descuentos = descuentos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Encomienda getEncomienda() {
        return encomienda;
    }

    public void setEncomienda(Encomienda encomienda) {
        this.encomienda = encomienda;
    }

    public String getCedula_cliente() {
        return cedula_cliente;
    }

    public void setCedula_cliente(String cedula_cliente) {
        this.cedula_cliente = cedula_cliente;
    }

    public boolean isEstado_factura() {
        return estado_factura;
    }

    public void setEstado_factura(boolean estado_factura) {
        this.estado_factura = estado_factura;
    }
    
    // Método para crear una factura basada en una encomienda y un cliente
    public static Factura crearFactura(Encomienda encomienda, Usuario cliente) {
        Factura factura = new Factura();
        factura.idFactura = UUID.randomUUID().toString(); // Genera un ID único para la factura
        factura.fecha = LocalDate.now();
        factura.impuestos = calcularImpuestos(encomienda);
        factura.descuentos = calcularDescuentos(encomienda, cliente);
        factura.total = encomienda.calcularPrecioTotal() + factura.impuestos - factura.descuentos;
        factura.setEncomienda(encomienda);
        factura.setCedula_cliente(cliente.getCedula());
        factura.setEstado_factura(true); // Asume que la factura está activa al momento de creación
        return factura;
    }

    // Método para calcular los impuestos basados en la encomienda
    private static Double calcularImpuestos(Encomienda encomienda) {
        return encomienda.calcularPrecioTotal() * 0.15; // 15% de impuestos
    }

    // Método para calcular descuentos
    private static Double calcularDescuentos(Encomienda encomienda, Usuario cliente) {
        return 0.0; // No hay descuentos por defecto
    }
}
