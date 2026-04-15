package com.rodolfogodinez.kinalapp.controller;

import com.rodolfogodinez.kinalapp.entity.Usuario;
import com.rodolfogodinez.kinalapp.service.IUsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final IUsuarioService usuarioService;

    public LoginController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username,
                                @RequestParam String password,
                                HttpSession session,
                                Model model) {

        try {
            // Buscar usuario por username
            Usuario usuario = usuarioService.buscarPorUsername(username)
                    .orElse(null);

            // Validar credenciales
            if (usuario != null && usuario.getPassword().equals(password)) {

                // Verificar si el usuario está activo
                if (usuario.getEstado() == 0) {
                    model.addAttribute("error", "Usuario inactivo. Contacte al administrador.");
                    return "login";
                }

                // Guardar usuario en sesión
                session.setAttribute("usuario", usuario);
                session.setAttribute("nombreUsuario", usuario.getUsername());
                session.setAttribute("rol", usuario.getRol());

                return "redirect:/";
            } else {
                model.addAttribute("error", "Usuario o contraseña incorrectos");
                return "login";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error al iniciar sesión: " + e.getMessage());
            return "login";
        }
    }

    @PostMapping("/registro")
    public String procesarRegistro(@RequestParam String username,
                                   @RequestParam String email,
                                   @RequestParam String password,
                                   @RequestParam String confirmPassword,
                                   @RequestParam(defaultValue = "USER") String rol,
                                   Model model) {

        try {
            // Validar que las contraseñas coincidan
            if (!password.equals(confirmPassword)) {
                model.addAttribute("error", "Las contraseñas no coinciden");
                return "login";
            }

            // Validar longitud de contraseña
            if (password.length() < 6) {
                model.addAttribute("error", "La contraseña debe tener al menos 6 caracteres");
                return "login";
            }

            // Verificar si el usuario ya existe
            if (usuarioService.existeUsername(username)) {
                model.addAttribute("error", "El nombre de usuario ya está en uso");
                return "login";
            }

            // Verificar si el email ya existe
            if (usuarioService.existeEmail(email)) {
                model.addAttribute("error", "El correo electrónico ya está registrado");
                return "login";
            }

            // Crear nuevo usuario
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setUsername(username);
            nuevoUsuario.setEmail(email);
            nuevoUsuario.setPassword(password);
            nuevoUsuario.setRol(rol);
            nuevoUsuario.setEstado(1); // Activo por defecto

            usuarioService.guardar(nuevoUsuario);

            model.addAttribute("success", "¡Cuenta creada exitosamente! Ahora puedes iniciar sesión.");
            return "login";

        } catch (Exception e) {
            model.addAttribute("error", "Error al crear la cuenta: " + e.getMessage());
            return "login";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}