package com.rodolfogodinez.kinalapp.service;

import com.rodolfogodinez.kinalapp.entity.Producto;
import java.util.List;
import java.util.Optional;

public interface IProductoService {

    // Listar todos los productos
    List<Producto> listarTodos();

    // Listar productos activos
    List<Producto> listarActivos();

    // Buscar producto por su código
    Optional<Producto> buscarPorCodigo(Integer codigo);


    Producto guardar(Producto producto);


    Producto actualizar(Integer codigo, Producto producto);


    void eliminar(Integer codigo);


    boolean existePorCodigo(Integer codigo);


    List<Producto> buscarPorNombre(String nombre);

    // Actualizar stock después de una venta
    Producto actualizarStock(Integer codigo, Integer cantidadVendida);
}