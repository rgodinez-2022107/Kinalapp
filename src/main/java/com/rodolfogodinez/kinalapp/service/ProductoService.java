package com.rodolfogodinez.kinalapp.service;

import com.rodolfogodinez.kinalapp.entity.Producto;
import com.rodolfogodinez.kinalapp.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService implements IProductoService {

    private final ProductoRepository productoRepository;

    // Inyección por constructor
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarActivos() {
        return productoRepository.findByEstado(1);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> buscarPorCodigo(Integer codigo) {
        return productoRepository.findById(codigo);
    }

    @Override
    public Producto guardar(Producto producto) {
        // Validar datos del producto
        validarProducto(producto);

        // Si no se especifica estado, se activa por defecto
        if (producto.getEstado() == null || producto.getEstado() == 0) {
            producto.setEstado(1);
        }

        // Si no se especifica stock, se inicializa en 0
        if (producto.getStock() == null) {
            producto.setStock(0);
        }

        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizar(Integer codigo, Producto producto) {
        // Verificar que el producto existe
        if (!productoRepository.existsById(codigo)) {
            throw new RuntimeException("Producto no encontrado con código: " + codigo);
        }

        // Asegurar que el código del objeto coincida con el de la URL
        producto.setCodigoProducto(codigo);

        // Validar datos
        validarProducto(producto);

        return productoRepository.save(producto);
    }

    @Override
    public void eliminar(Integer codigo) {
        if (!productoRepository.existsById(codigo)) {
            throw new RuntimeException("Producto no encontrado con código: " + codigo);
        }
        productoRepository.deleteById(codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorCodigo(Integer codigo) {
        return productoRepository.existsById(codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreProductoContainingIgnoreCase(nombre);
    }

    @Override
    public Producto actualizarStock(Integer codigo, Integer cantidadVendida) {
        Producto producto = productoRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Integer nuevoStock = producto.getStock() - cantidadVendida;

        if (nuevoStock < 0) {
            throw new IllegalArgumentException("Stock insuficiente. Stock actual: " + producto.getStock());
        }

        producto.setStock(nuevoStock);
        return productoRepository.save(producto);
    }

    // Método privado para validaciones
    private void validarProducto(Producto producto) {
        if (producto.getNombreProducto() == null || producto.getNombreProducto().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }

        if (producto.getPrecio() == null || producto.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }

        if (producto.getStock() == null || producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
    }
}