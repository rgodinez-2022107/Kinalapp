package com.rodolfogodinez.kinalapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_usuario")
    private Integer codigoUsuario;

    @Column(length = 45, nullable = false, unique = true)
    // unique = true para que no haya dos usuarios con el mismo username
    private String username;

    @Column(length = 45, nullable = false)
    private String password;

    @Column(length = 60, nullable = false, unique = true) // El email también debe ser único
    private String email;

    @Column(length = 45, nullable = false)
    private String rol; // admin, venddor

    @Column(nullable = false)
    private Integer estado;


    public Usuario() {
    }

    public Usuario(String username, String password, String email, String rol, Integer estado) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.rol = rol;
        this.estado = estado;
    }


    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}