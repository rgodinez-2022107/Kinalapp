package com.rodolfogodinez.kinalapp.service;

import com.rodolfogodinez.kinalapp.entity.Ventas;
import com.rodolfogodinez.kinalapp.entity.Cliente;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IVentasService {

    List<Ventas> listarTodos();

    List<Ventas> listarActivas();

    Optional<Ventas> buscarPorCodigo(Long codigo);

    Ventas guardar(Ventas venta);

    Ventas actualizar(Long codigo, Ventas venta);

    void eliminar(Long codigo);

    boolean existePorCodigo(Long codigo);

    List<Ventas> buscarPorCliente(Cliente cliente);

    List<Ventas> buscarPorFecha(LocalDate fecha);

    List<Ventas> buscarPorRangoFechas(LocalDate inicio, LocalDate fin);

    Ventas anularVenta(Long codigo);
}