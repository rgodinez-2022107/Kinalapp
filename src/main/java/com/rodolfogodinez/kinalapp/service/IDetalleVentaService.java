package com.rodolfogodinez.kinalapp.service;

import com.rodolfogodinez.kinalapp.entity.DetalleVenta;
import com.rodolfogodinez.kinalapp.entity.Ventas;
import java.util.List;
import java.util.Optional;

public interface IDetalleVentaService {

    List<DetalleVenta> listarTodos();

    Optional<DetalleVenta> buscarPorCodigo(Integer codigo);

    DetalleVenta guardar(DetalleVenta detalle);

    DetalleVenta actualizar(Integer codigo, DetalleVenta detalle);

    void eliminar(Integer codigo);

    boolean existePorCodigo(Integer codigo);

    List<DetalleVenta> buscarPorVenta(Ventas venta);

    Double recalcularTotalVenta(Integer codigoVenta);

    List<DetalleVenta> buscarPorVenta(Integer codigoVenta);
}