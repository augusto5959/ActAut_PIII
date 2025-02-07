package com.spring_app.Controlador;

import com.spring_app.Entidad.Cliente;
import com.spring_app.Servicio.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    // LISTAR CLIENTES
    @GetMapping
    public String listarClientes(@RequestParam(required = false) String buscarCliente, Model model) {
        List<Cliente> clientes = clienteServicio.buscarClientePorNombre(buscarCliente);
        model.addAttribute("clientes", clientes);
        model.addAttribute("buscarCliente", buscarCliente);
        return "pages/Cliente/listaClientes"; // Ruta de la vista
    }

    // MOSTRAR FORMULARIO DE CLIENTE
    @GetMapping("/formulario")
    public String mostrarFormularioCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "pages/Cliente/formularioCliente"; // Ruta de la vista
    }

    // GUARDAR CLIENTE
    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente) {
        clienteServicio.guardarCliente(cliente);
        return "redirect:/clientes"; // Redirige a la lista de clientes
    }

    // EDITAR CLIENTE
    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        Optional<Cliente> cliente = clienteServicio.buscarClientePorId(id);
        cliente.ifPresent(c -> model.addAttribute("cliente", c));
        return "pages/Cliente/formularioCliente"; // Ruta de la vista
    }

    // ELIMINAR CLIENTE
    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteServicio.eliminarCliente(id);
        return "redirect:/clientes"; // Redirige a la lista de clientes
    }
}