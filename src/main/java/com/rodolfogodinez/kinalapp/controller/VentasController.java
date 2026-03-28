package com.rodolfogodinez.kinalapp.controller;

import com.rodolfogodinez.kinalapp.entity.Ventas;
import com.rodolfogodinez.kinalapp.service.IVentasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentasController {

    private final IVentasService ventasService;

    public VentasController(IVentasService ventasService) {
        this.ventasService = ventasService;
    }

    @GetMapping
    public ResponseEntity<List<Ventas>> listar() {
        return ResponseEntity.ok(ventasService.listarTodos());
    }

    @GetMapping("/activas")
    public ResponseEntity<List<Ventas>> listarActivas() {
        return ResponseEntity.ok(ventasService.listarActivas());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Ventas> buscarPorCodigo(@PathVariable Integer codigo) {
        return ventasService.buscarPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Ventas venta) {
        try {
            System.out.println("Recibiendo venta: " + venta);
            Ventas nuevaVenta = ventasService.guardar(venta);
            return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> actualizar(@PathVariable Integer codigo, @RequestBody Ventas venta) {
        try {
            if (!ventasService.existePorCodigo(codigo)) {
                return ResponseEntity.notFound().build();
            }
            Ventas ventaActualizada = ventasService.actualizar(codigo, venta);
            return ResponseEntity.ok(ventaActualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{codigo}/anular")
    public ResponseEntity<?> anularVenta(@PathVariable Integer codigo) {
        try {
            Ventas ventaAnulada = ventasService.anularVenta(codigo);
            return ResponseEntity.ok(ventaAnulada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer codigo) {
        try {
            if (!ventasService.existePorCodigo(codigo)) {
                return ResponseEntity.notFound().build();
            }
            ventasService.eliminar(codigo);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}