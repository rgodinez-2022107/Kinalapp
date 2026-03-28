package com.rodolfogodinez.kinalapp.service;

import com.rodolfogodinez.kinalapp.entity.Ventas;
import com.rodolfogodinez.kinalapp.entity.Cliente;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IVentasService {


    List<Ventas> listarTodos();

    List<Ventas> listarActivas();

    Optional<Ventas> buscarPorCodigo(Integer codigo);

    Ventas guardar(Ventas venta);

    Ventas actualizar(Integer codigo, Ventas venta);

    void eliminar(Integer codigo);

    boolean existePorCodigo(Integer codigo);

    List<Ventas> buscarPorCliente(Cliente cliente);

    List<Ventas> buscarPorFecha(LocalDate fecha);

    List<Ventas> buscarPorRangoFechas(LocalDate inicio, LocalDate fin);

    Ventas anularVenta(Integer codigo);
}