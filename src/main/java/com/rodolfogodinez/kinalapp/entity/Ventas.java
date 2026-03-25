package com.rodolfogodinez.kinalapp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ventas")
public class Ventas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "codigo_venta")
    private Integer codigoVenta;// lo puse asi para que sea un toincremental
    @Column(name = "fecha_venta", nullable = false)// significa que n puede ser nullo
    private LocalDate fechaVenta; // use localdate para la fecha
    @Column(nullable = false)
    private Double total;// "Double" para decimales
    @Column(nullable = false)
    private Integer estado; // para saver si el cliente esta activo o inactivo


    @ManyToOne // porque muchas ventas pueden venir de un solo cliente.
    @JoinColumn(name = "clientes_dpi_cliente", referencedColumnName = "dpi_cliente", nullable = false)
    private Cliente cliente; // Este es objeto Cliente relacionado

    @ManyToOne
    @JoinColumn(name = "usuarios_codigo_usuario", referencedColumnName = "codigo_usuario", nullable = false)
    private Usuario usuario; // este es el objeto Usuario relacionado


    public Ventas() {

    }

    // constructor con parametros pero sin codigoVenta porque ya lo puse como toincremental

    public Ventas(LocalDate fechaVenta, Double total, Integer estado, Cliente cliente, Usuario usuario) {
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.estado = estado;
        this.cliente = cliente;
        this.usuario = usuario;
    }

    public Integer getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(Integer codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

