package com.rodolfogodinez.kinalapp.repository;

import com.rodolfogodinez.kinalapp.entity.DetalleVenta;
import com.rodolfogodinez.kinalapp.entity.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    List<DetalleVenta> findByVenta(Ventas venta);

    List<DetalleVenta> findByVenta_CodigoVenta(Long codigoVenta);
}