package com.rodolfogodinez.kinalapp.service;

import com.rodolfogodinez.kinalapp.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    // Listar todos los usuarios
    List<Usuario> listarTodos();

    // Listar usuarios activos
    List<Usuario> listarActivos();

    // Buscar usuario por código
    Optional<Usuario> buscarPorCodigo(Integer codigo);

    // Buscar usuario por username (para login)
    Optional<Usuario> buscarPorUsername(String username);

    // Buscar usuario por email
    Optional<Usuario> buscarPorEmail(String email);

    // Guardar un nuevo usuario
    Usuario guardar(Usuario usuario);

    // Actualizar un usuario existente
    Usuario actualizar(Integer codigo, Usuario usuario);

    // Eliminar un usuario
    void eliminar(Integer codigo);

    // Verificar si existe un usuario
    boolean existePorCodigo(Integer codigo);

    // Verificar si existe username
    boolean existeUsername(String username);

    // Verificar si existe email
    boolean existeEmail(String email);

    // Buscar usuarios por rol
    List<Usuario> buscarPorRol(String rol);
}