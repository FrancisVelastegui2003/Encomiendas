/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package encomiendas.model.entity.facturacion;

import encomiendas.model.entity.encomiendas.Encomienda;
import encomiendas.model.entity.usuarios.Usuario;
import java.time.LocalDate;

/**
 *
 * @author Lenovo
 */
public class Factura {
    private Integer idFactura;
    private LocalDate fecha;
    private Double impuestos;
    private Double descuentos;
    private Double total;
    
    public Factura(){
        
    }
    
    public Factura(Integer idFactura, LocalDate fecha, Double impuestos, Double descuentos, Double total) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.impuestos = impuestos;
        this.descuentos = descuentos;
        this.total = total;
    }

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
    
    /**
     * Permite crear una factura
     * @param encomienda    La encomienda
     * @param cliente       El cliente
     * @return              La factura
     */
    public static Factura crearFactura(Encomienda encomienda, Usuario cliente) {
        Factura factura = new Factura();
        factura.fecha = LocalDate.now();
        factura.impuestos = calcularImpuestos(encomienda);
        factura.descuentos = calcularDescuentos(encomienda, cliente);
        factura.total = encomienda.calcularPrecioTotal();
        factura.calcularTotal();
        return factura;
    }
    
    /**
     * Permite calcular el total a pagar en la factura
     */
    public void calcularTotal() {
        // Asumiendo que el total es el precio calculado con impuestos y descuentos aplicados
        this.total = this.total + this.impuestos - this.descuentos;
    }
    
    /**
     * Permite calcular los impuestos aplicables a una encomienda
     * @param encomienda    La encomienda
     * @return              Impuestos aplicables
     */
    private static Double calcularImpuestos(Encomienda encomienda) {
        return encomienda.calcularPrecioTotal() * 0.12; // 12% de impuestos (IVA)
    }
    
    /**
     * Permite calcular lo descuentos aplicacbles, en este caso se asume que 
     * puede ser por tipo de cliente o tipo de encomienda
     * @param encomienda    La encomienda
     * @param cliente       El cliente
     * @return              Descuento aplicable
     */
    private static Double calcularDescuentos(Encomienda encomienda, Usuario cliente) {
        return 0.0; // No hay descuentos aplicables por defecto
    }
}