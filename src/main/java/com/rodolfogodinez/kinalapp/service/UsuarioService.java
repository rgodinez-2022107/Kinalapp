package com.rodolfogodinez.kinalapp.service;

import com.rodolfogodinez.kinalapp.entity.Usuario;
import com.rodolfogodinez.kinalapp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarActivos() {
        return usuarioRepository.findByEstado(1);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorCodigo(Long codigo) {
        return usuarioRepository.findById(codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        validarUsuario(usuario);
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new IllegalArgumentException("El username ya está en uso");
        }
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("El email ya está en uso");
        }
        if (usuario.getEstado() == null || usuario.getEstado() == 0) {
            usuario.setEstado(1);
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizar(Long codigo, Usuario usuario) {
        if (!usuarioRepository.existsById(codigo)) {
            throw new RuntimeException("Usuario no encontrado con código: " + codigo);
        }
        Usuario usuarioExistente = usuarioRepository.findById(codigo).get();
        if (!usuarioExistente.getUsername().equals(usuario.getUsername()) &&
                usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new IllegalArgumentException("El username ya está en uso");
        }
        if (!usuarioExistente.getEmail().equals(usuario.getEmail()) &&
                usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("El email ya está en uso");
        }
        usuario.setCodigoUsuario(codigo);
        validarUsuario(usuario);
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(Long codigo) {
        if (!usuarioRepository.existsById(codigo)) {
            throw new RuntimeException("Usuario no encontrado con código: " + codigo);
        }
        usuarioRepository.deleteById(codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorCodigo(Long codigo) {
        return usuarioRepository.existsById(codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> buscarPorRol(String rol) {
        return usuarioRepository.findByRol(rol);
    }

    private void validarUsuario(Usuario usuario) {
        if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("El username es obligatorio");
        }
        if (usuario.getPassword() == null || usuario.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }
        if (usuario.getRol() == null || usuario.getRol().trim().isEmpty()) {
            throw new IllegalArgumentException("El rol es obligatorio");
        }
        if (!usuario.getEmail().contains("@")) {
            throw new IllegalArgumentException("El email no tiene un formato válido");
        }
    }
}