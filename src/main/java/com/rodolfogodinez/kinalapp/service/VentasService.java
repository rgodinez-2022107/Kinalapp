package com.rodolfogodinez.kinalapp.service;

import com.rodolfogodinez.kinalapp.entity.Ventas;
import com.rodolfogodinez.kinalapp.entity.Cliente;
import com.rodolfogodinez.kinalapp.repository.VentasRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VentasService implements IVentasService {

    private final VentasRepository ventasRepository;

    public VentasService(VentasRepository ventasRepository) {
        this.ventasRepository = ventasRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ventas> listarTodos() {
        return ventasRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ventas> listarActivas() {
        return ventasRepository.findByEstado(1);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Ventas> buscarPorCodigo(Integer codigo) {
        return ventasRepository.findById(codigo);
    }

    @Override
    public Ventas guardar(Ventas venta) {
        validarVenta(venta);

        // Si no se especifica fecha, usar la fecha actual
        if (venta.getFechaVenta() == null) {
            venta.setFechaVenta(LocalDate.now());
        }

        // Si no se especifica estado, activar por defecto
        if (venta.getEstado() == null || venta.getEstado() == 0) {
            venta.setEstado(1);
        }

        // Si no se especifica total, inicializar en 0 (luego se calcula con los detalles)
        if (venta.getTotal() == null) {
            venta.setTotal(0.0);
        }

        return ventasRepository.save(venta);
    }

    @Override
    public Ventas actualizar(Integer codigo, Ventas venta) {
        if (!ventasRepository.existsById(codigo)) {
            throw new RuntimeException("Venta no encontrada con código: " + codigo);
        }

        venta.setCodigoVenta(codigo);
        validarVenta(venta);

        return ventasRepository.save(venta);
    }

    @Override
    public void eliminar(Integer codigo) {
        if (!ventasRepository.existsById(codigo)) {
            throw new RuntimeException("Venta no encontrada con código: " + codigo);
        }
        ventasRepository.deleteById(codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorCodigo(Integer codigo) {
        return ventasRepository.existsById(codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ventas> buscarPorCliente(Cliente cliente) {
        return ventasRepository.findByCliente(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ventas> buscarPorFecha(LocalDate fecha) {
        return ventasRepository.findByFechaVenta(fecha);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ventas> buscarPorRangoFechas(LocalDate inicio, LocalDate fin) {
        return ventasRepository.findByFechaVentaBetween(inicio, fin);
    }

    @Override
    public Ventas anularVenta(Integer codigo) {
        Ventas venta = ventasRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        venta.setEstado(0); // Estado 0 = anulada/inactiva
        return ventasRepository.save(venta);
    }

    private void validarVenta(Ventas venta) {
        if (venta.getCliente() == null) {
            throw new IllegalArgumentException("El cliente es obligatorio");
        }

        if (venta.getUsuario() == null) {
            throw new IllegalArgumentException("El usuario que realiza la venta es obligatorio");
        }

        if (venta.getTotal() != null && venta.getTotal() < 0) {
            throw new IllegalArgumentException("El total no puede ser negativo");
        }
    }
}