package com.rodolfogodinez.kinalapp.controller;

import com.rodolfogodinez.kinalapp.entity.Usuario;
import com.rodolfogodinez.kinalapp.service.IUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/usuarios")

public class UsuarioController {


    private final IUsuarioService usuarioService;


    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
        // 200 OK con la lista de usuarios
    }


    @GetMapping("/activos")
    public ResponseEntity<List<Usuario>> listarActivos() {
        List<Usuario> usuarios = usuarioService.listarActivos();
        return ResponseEntity.ok(usuarios);

    }


    @GetMapping("/{codigo}")
    public ResponseEntity<Usuario> buscarPorCodigo(@PathVariable Integer codigo) {

        return usuarioService.buscarPorCodigo(codigo)

                .map(ResponseEntity::ok)

                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/username/{username}")
    public ResponseEntity<Usuario> buscarPorUsername(@PathVariable String username) {
        return usuarioService.buscarPorUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Usuario usuario) {

        try {
            Usuario nuevoUsuario = usuarioService.guardar(usuario);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }


    @PutMapping("/{codigo}")
    public ResponseEntity<?> actualizar(@PathVariable Integer codigo, @RequestBody Usuario usuario) {
        try {
            if (!usuarioService.existePorCodigo(codigo)) {
                return ResponseEntity.notFound().build();
            }
            Usuario usuarioActualizado = usuarioService.actualizar(codigo, usuario);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer codigo) {
        try {
            if (!usuarioService.existePorCodigo(codigo)) {
                return ResponseEntity.notFound().build();
            }
            usuarioService.eliminar(codigo);
            return ResponseEntity.noContent().build();
            // 204 NO CONTENT (se ejecuto correctamente y no devuelve cuerpo)
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}