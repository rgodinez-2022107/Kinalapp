package com.rodolfogodinez.kinalapp.repository;

import com.rodolfogodinez.kinalapp.entity.Ventas;
import com.rodolfogodinez.kinalapp.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface VentasRepository extends JpaRepository<Ventas, Long> {

    List<Ventas> findByCliente(Cliente cliente);

    List<Ventas> findByEstado(int estado);

    List<Ventas> findByFechaVenta(LocalDate fecha);

    List<Ventas> findByFechaVentaBetween(LocalDate fechaInicio, LocalDate fechaFin);

    List<Ventas> findByClienteAndEstado(Cliente cliente, int estado);
}