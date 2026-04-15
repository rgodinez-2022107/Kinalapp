package com.rodolfogodinez.kinalapp.controller;

import com.rodolfogodinez.kinalapp.entity.Cliente;
import com.rodolfogodinez.kinalapp.entity.Producto;
import com.rodolfogodinez.kinalapp.entity.Ventas;
import com.rodolfogodinez.kinalapp.service.IClienteService;
import com.rodolfogodinez.kinalapp.service.IProductoService;
import com.rodolfogodinez.kinalapp.service.IVentasService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class DashboardController {

    private final IClienteService clienteService;
    private final IProductoService productoService;
    private final IVentasService ventasService;

    public DashboardController(IClienteService clienteService,
                               IProductoService productoService,
                               IVentasService ventasService) {
        this.clienteService = clienteService;
        this.productoService = productoService;
        this.ventasService = ventasService;
    }

    @GetMapping("/")
    public String dashboard(@RequestParam(value = "seccion", defaultValue = "clientes") String seccion,
                            Model model,
                            HttpSession session) {

        // Verificar si hay usuario en sesión
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        model.addAttribute("seccionActual", seccion);

        // Siempre cargar clientes y productos para el formulario de ventas
        List<Cliente> clientes = clienteService.listarTodos();
        List<Producto> productos = productoService.listarTodos();
        model.addAttribute("listaClientes", clientes);
        model.addAttribute("listaProductos", productos);

        if (seccion.equals("clientes")) {
            model.addAttribute("tituloTabla", "Clientes Registrados");
            model.addAttribute("tituloFormulario", "Gestión de Clientes");
            model.addAttribute("clienteForm", new Cliente());
        } else if (seccion.equals("productos")) {
            model.addAttribute("tituloTabla", "Productos en Catálogo");
            model.addAttribute("tituloFormulario", "Gestión de Productos");
            model.addAttribute("productoForm", new Producto());
        } else if (seccion.equals("ventas")) {
            model.addAttribute("tituloTabla", "Historial de Ventas");
            model.addAttribute("tituloFormulario", "Nueva Venta");
        }

        List<Ventas> ventas = ventasService.listarTodos();
        model.addAttribute("listaVentas", ventas);

        return "dashboard";
    }



    @PostMapping("/guardarCliente")
    public String guardarCliente(@ModelAttribute Cliente cliente,
                                 RedirectAttributes redirectAttributes) {
        try {
            if (cliente.getEstado() == null) {
                cliente.setEstado(1);
            }
            clienteService.guardar(cliente);
            redirectAttributes.addFlashAttribute("mensajeExito", "Cliente guardado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error: " + e.getMessage());
        }
        return "redirect:/?seccion=clientes";
    }

    @PostMapping("/guardarProducto")
    public String guardarProducto(@ModelAttribute Producto producto,
                                  RedirectAttributes redirectAttributes) {
        try {
            if (producto.getEstado() == null) {
                producto.setEstado(1);
            }
            if (producto.getStock() == null) {
                producto.setStock(0);
            }
            productoService.guardar(producto);
            redirectAttributes.addFlashAttribute("mensajeExito", "Producto guardado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error: " + e.getMessage());
        }
        return "redirect:/?seccion=productos";
    }

    @GetMapping("/clientes/editar")
    public String editarCliente(@RequestParam String dpi, RedirectAttributes redirectAttributes) {
        clienteService.buscarPorDPI(dpi).ifPresent(cliente -> {
            redirectAttributes.addFlashAttribute("clienteForm", cliente);
        });
        return "redirect:/?seccion=clientes";
    }

    @GetMapping("/productos/editar")
    public String editarProducto(@RequestParam Long codigo, RedirectAttributes redirectAttributes) {
        productoService.buscarPorCodigo(codigo).ifPresent(producto -> {
            redirectAttributes.addFlashAttribute("productoForm", producto);
        });
        return "redirect:/?seccion=productos";
    }
}