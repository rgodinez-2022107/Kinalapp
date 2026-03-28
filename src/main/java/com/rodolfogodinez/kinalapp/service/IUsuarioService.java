package com.rodolfogodinez.kinalapp.service;

import com.rodolfogodinez.kinalapp.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> listarTodos();

    List<Usuario> listarActivos();

    Optional<Usuario> buscarPorCodigo(Long codigo);

    Optional<Usuario> buscarPorUsername(String username);

    Optional<Usuario> buscarPorEmail(String email);

    Usuario guardar(Usuario usuario);

    Usuario actualizar(Long codigo, Usuario usuario);

    void eliminar(Long codigo);

    boolean existePorCodigo(Long codigo);

    boolean existeUsername(String username);

    boolean existeEmail(String email);

    List<Usuario> buscarPorRol(String rol);
}