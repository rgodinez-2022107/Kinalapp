package com.rodolfogodinez.kinalapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//llave primario autoincremental
    @Column(name = "codigo_detalle_venta")
    private Integer codigoDetalleVenta;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    @Column(nullable = false)
    private Double subtotal; // lo va a calcular como cantidad por precioUnitario

    @ManyToOne //muchos detalles pertenecen a una venta
    @JoinColumn(name = "ventas_codigo_venta", referencedColumnName = "codigo_venta" =false)
    private Ventas venta;

    @ManyToOne
    @JoinColumn(name = "Productos_cadigo_producto", referencedColumnName = "codigo_producto", nullable = false)
    private Producto producto;

    public DetalleVenta() {

    }

    public DetalleVenta(Integer cantidad, Double precioUnitario, Double subtotal, Ventas venta, Producto producto) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.venta = venta;
        this.producto = producto;
    }


    public Integer getCodigoDetalleVenta() {
        return codigoDetalleVenta;
    }

    public void setCodigoDetalleVenta(Integer codigoDetalleVenta) {
        this.codigoDetalleVenta = codigoDetalleVenta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
        // this.subtotal = this.cantidad * this.precioUnitario;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
