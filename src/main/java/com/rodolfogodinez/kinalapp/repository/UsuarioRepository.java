package com.rodolfogodinez.kinalapp.repository;

import com.rodolfogodinez.kinalapp.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByEstado(int estado);

    List<Usuario> findByRol(String rol);

    // Para duplicados
    boolean existsByUsername(String username);

    // email du
    boolean existsByEmail(String email);
}