package com.rodolfogodinez.kinalapp.service;

import com.rodolfogodinez.kinalapp.entity.DetalleVenta;
import com.rodolfogodinez.kinalapp.entity.Ventas;
import com.rodolfogodinez.kinalapp.repository.DetalleVentaRepository;
import com.rodolfogodinez.kinalapp.repository.VentasRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetalleVentaService implements IDetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;
    private final VentasRepository ventasRepository;
    private final IProductoService productoService;

    public DetalleVentaService(DetalleVentaRepository detalleVentaRepository,
                               VentasRepository ventasRepository,
                               IProductoService productoService) {
        this.detalleVentaRepository = detalleVentaRepository;
        this.ventasRepository = ventasRepository;
        this.productoService = productoService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVenta> listarTodos() {
        return detalleVentaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DetalleVenta> buscarPorCodigo(Long codigo) {
        return detalleVentaRepository.findById(codigo);
    }

    @Override
    public DetalleVenta guardar(DetalleVenta detalle) {
        validarDetalle(detalle);
        if (detalle.getVenta() == null || detalle.getVenta().getCodigoVenta() == null) {
            throw new IllegalArgumentException("La venta es obligatoria");
        }
        if (!ventasRepository.existsById(detalle.getVenta().getCodigoVenta())) {
            throw new RuntimeException("La venta no existe");
        }
        if (detalle.getProducto() == null || detalle.getProducto().getCodigoProducto() == null) {
            throw new IllegalArgumentException("El producto es obligatorio");
        }
        Double subtotal = detalle.getCantidad() * detalle.getPrecioUnitario();
        detalle.setSubtotal(subtotal);
        DetalleVenta detalleGuardado = detalleVentaRepository.save(detalle);
        recalcularTotalVenta(detalle.getVenta().getCodigoVenta());
        productoService.actualizarStock(detalle.getProducto().getCodigoProducto(), detalle.getCantidad());
        return detalleGuardado;
    }

    @Override
    public DetalleVenta actualizar(Long codigo, DetalleVenta detalle) {
        if (!detalleVentaRepository.existsById(codigo)) {
            throw new RuntimeException("Detalle no encontrado con código: " + codigo);
        }
        DetalleVenta detalleOriginal = detalleVentaRepository.findById(codigo).get();
        detalle.setCodigoDetalleVenta(codigo);
        validarDetalle(detalle);
        Double nuevoSubtotal = detalle.getCantidad() * detalle.getPrecioUnitario();
        detalle.setSubtotal(nuevoSubtotal);
        DetalleVenta detalleActualizado = detalleVentaRepository.save(detalle);
        recalcularTotalVenta(detalle.getVenta().getCodigoVenta());
        Integer diferenciaCantidad = detalle.getCantidad() - detalleOriginal.getCantidad();
        if (diferenciaCantidad != 0) {
            productoService.actualizarStock(detalle.getProducto().getCodigoProducto(), diferenciaCantidad);
        }
        return detalleActualizado;
    }

    @Override
    public void eliminar(Long codigo) {
        DetalleVenta detalle = detalleVentaRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado con código: " + codigo));
        Long codigoVenta = detalle.getVenta().getCodigoVenta();
        Long codigoProducto = detalle.getProducto().getCodigoProducto();
        Integer cantidadDevuelta = detalle.getCantidad();
        detalleVentaRepository.deleteById(codigo);
        recalcularTotalVenta(codigoVenta);
        productoService.actualizarStock(codigoProducto, -cantidadDevuelta);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorCodigo(Long codigo) {
        return detalleVentaRepository.existsById(codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVenta> buscarPorVenta(Ventas venta) {
        if (venta == null || venta.getCodigoVenta() == null) {
            throw new IllegalArgumentException("La venta es obligatoria");
        }
        return detalleVentaRepository.findByVenta(venta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVenta> buscarPorVentaId(Long codigoVenta) {
        return detalleVentaRepository.findByVenta_CodigoVenta(codigoVenta);
    }

    @Override
    public Double recalcularTotalVenta(Long codigoVenta) {
        Ventas venta = ventasRepository.findById(codigoVenta)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con código: " + codigoVenta));
        List<DetalleVenta> detalles = buscarPorVentaId(codigoVenta);
        Double nuevoTotal = detalles.stream()
                .mapToDouble(DetalleVenta::getSubtotal)
                .sum();
        venta.setTotal(nuevoTotal);
        ventasRepository.save(venta);
        return nuevoTotal;
    }

    private void validarDetalle(DetalleVenta detalle) {
        if (detalle.getCantidad() == null || detalle.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        if (detalle.getPrecioUnitario() == null || detalle.getPrecioUnitario() <= 0) {
            throw new IllegalArgumentException("El precio unitario debe ser mayor a 0");
        }
        if (detalle.getProducto() == null) {
            throw new IllegalArgumentException("El producto es obligatorio");
        }
        if (detalle.getVenta() == null) {
            throw new IllegalArgumentException("La venta es obligatoria");
        }
    }
}