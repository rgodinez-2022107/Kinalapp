package com.rodolfogodinez.kinalapp.repository;

import com.rodolfogodinez.kinalapp.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    // Buscar cosas por estado
    List<Producto> findByEstado(int estado);

    // Por nobre
    List<Producto> findByNombreProductoContainingIgnoreCase(String nombre);

    // verificar si esta dispnible
    List<Producto> findByStockGreaterThan(Integer stock);
}