package com.rodolfogodinez.kinalapp.service;

import com.rodolfogodinez.kinalapp.entity.Producto;
import java.util.List;
import java.util.Optional;

public interface IProductoService {

    List<Producto> listarTodos();

    List<Producto> listarActivos();

    Optional<Producto> buscarPorCodigo(Long codigo);

    Producto guardar(Producto producto);

    Producto actualizar(Long codigo, Producto producto);

    void eliminar(Long codigo);

    boolean existePorCodigo(Long codigo);

    List<Producto> buscarPorNombre(String nombre);

    Producto actualizarStock(Long codigo, Integer cantidadVendida);
}