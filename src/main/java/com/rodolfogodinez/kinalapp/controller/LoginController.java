package com.rodolfogodinez.kinalapp.controller;

import com.rodolfogodinez.kinalapp.entity.Usuario;
import com.rodolfogodinez.kinalapp.service.IUsuarioService;
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
    public String mostrarLogin(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout,
                               Model model) {
        if (error != null) {
            model.addAttribute("error", "Usuario o contrasena incorrectos, o usuario inactivo.");
        }
        if (logout != null) {
            model.addAttribute("success", "Has cerrado sesion exitosamente.");
        }
        return "login";
    }

    @PostMapping("/registro")
    public String procesarRegistro(@RequestParam String username,
                                   @RequestParam String email,
                                   @RequestParam String password,
                                   @RequestParam String confirmPassword,
                                   Model model) {
        try {
            if (!password.equals(confirmPassword)) {
                model.addAttribute("error", "Las contrasenas no coinciden");
                model.addAttribute("showRegister", true);
                return "login";
            }

            if (password.length() < 6) {
                model.addAttribute("error", "La contrasena debe tener al menos 6 caracteres");
                model.addAttribute("showRegister", true);
                return "login";
            }

            if (usuarioService.existeUsername(username)) {
                model.addAttribute("error", "El nombre de usuario ya esta en uso");
                model.addAttribute("showRegister", true);
                return "login";
            }

            if (usuarioService.existeEmail(email)) {
                model.addAttribute("error", "El correo electronico ya esta registrado");
                model.addAttribute("showRegister", true);
                return "login";
            }

            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setUsername(username);
            nuevoUsuario.setEmail(email);
            nuevoUsuario.setPassword(password);
            nuevoUsuario.setRol("USER");
            nuevoUsuario.setEstado(1);

            usuarioService.guardar(nuevoUsuario);

            model.addAttribute("success", "Cuenta creada exitosamente. Ahora puedes iniciar sesion.");
            return "login";

        } catch (Exception e) {
            model.addAttribute("error", "Error al crear la cuenta: " + e.getMessage());
            model.addAttribute("showRegister", true);
            return "login";
        }
    }
}
