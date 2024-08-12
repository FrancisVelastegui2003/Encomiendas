package encomiendas.model.entity.facturacion;

import encomiendas.model.entity.encomiendas.Encomienda;
import encomiendas.model.entity.usuarios.Usuario;
import java.time.LocalDate;

public class Factura {
    private Integer idFactura;
    private LocalDate fecha;
    private Double impuestos;
    private Double descuentos;
    private Double total;
    private Encomienda id_encomienda; // conexion con encomienda
    private Integer cedula_cliente;
    private boolean estado_factura;
    
    public Factura() {}

    public Factura(Integer idFactura, LocalDate fecha, Double impuestos, Double descuentos, Double total, Encomienda encomienda, Integer cedula_cliente, boolean estado_factura) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.impuestos = impuestos;
        this.descuentos = descuentos;
        this.total = total;
        this.id_encomienda = encomienda;
        this.cedula_cliente = cedula_cliente;
        this.estado_factura = estado_factura;
    }

    // Getters y Setters

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
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
        return id_encomienda;
    }

    public void setEncomienda(Encomienda encomienda) {
        this.id_encomienda = encomienda;
    }

    public Integer getCedula_cliente() {
        return cedula_cliente;
    }

    public void setCedula_cliente(Integer cedula_cliente) {
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
        factura.fecha = LocalDate.now();
        factura.impuestos = calcularImpuestos(encomienda);
        factura.descuentos = calcularDescuentos(encomienda, cliente);
        factura.total = encomienda.calcularPrecioTotal();
        factura.calcularTotal();
        factura.setEncomienda(encomienda);
        return factura;
    }

    // Método para calcular el total después de aplicar impuestos y descuentos
    public void calcularTotal() {
        this.total = this.total + this.impuestos - this.descuentos;
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
