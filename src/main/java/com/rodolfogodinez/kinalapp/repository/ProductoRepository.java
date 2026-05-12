package com.rodolfogodinez.kinalapp.repository;

import com.rodolfogodinez.kinalapp.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByEstado(int estado);

    List<Producto> findByNombreProductoContainingIgnoreCase(String nombre);

    List<Producto> findByStockGreaterThan(Integer stock);
}