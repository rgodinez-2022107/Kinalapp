package com.rodolfogodinez.kinalapp.service;

import com.rodolfogodinez.kinalapp.entity.DetalleVenta;
import com.rodolfogodinez.kinalapp.entity.Ventas;
import java.util.List;
import java.util.Optional;

public interface IDetalleVentaService {

    List<DetalleVenta> listarTodos();

    Optional<DetalleVenta> buscarPorCodigo(Long codigo);

    DetalleVenta guardar(DetalleVenta detalle);

    DetalleVenta actualizar(Long codigo, DetalleVenta detalle);

    void eliminar(Long codigo);

    boolean existePorCodigo(Long codigo);

    List<DetalleVenta> buscarPorVenta(Ventas venta);

    List<DetalleVenta> buscarPorVentaId(Long codigoVenta);

    Double recalcularTotalVenta(Long codigoVenta);
}