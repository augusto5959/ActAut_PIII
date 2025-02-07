package com.spring_app.Controlador;

import com.spring_app.Entidad.Cliente;
import com.spring_app.Entidad.Factura;
import com.spring_app.Entidad.Producto;
import com.spring_app.Servicio.ClienteServicio;
import com.spring_app.Servicio.FacturaServicio;
import com.spring_app.Servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/facturas") // Ruta base para todas las operaciones de facturas
public class FacturaControlador {

    @Autowired
    private FacturaServicio facturaServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private ProductoServicio productoServicio;

    // LISTAR FACTURAS
    @GetMapping
    public String listarFacturas(@RequestParam(name = "buscarFactura", required = false, defaultValue = "") String buscarFactura, Model model) {
        List<Factura> facturas = facturaServicio.listarFacturas(); // Puedes agregar lógica de búsqueda si es necesario
        model.addAttribute("facturas", facturas);
        model.addAttribute("buscarFactura", buscarFactura);
        return "pages/Factura/listaFacturas"; // Ruta de la vista
    }

    // MOSTRAR FORMULARIO DE FACTURA
    @GetMapping("/formularioFactura")
    public String mostrarFormularioFactura(Model model) {
        List<Cliente> clientes = clienteServicio.listarClientes();
        List<Producto> productos = productoServicio.listarProductos();

        // Crear una nueva factura
        Factura factura = new Factura();

        // Si hay productos, establecer el precio del primer producto por defecto
        if (!productos.isEmpty()) {
            factura.setPrecio(productos.get(0).getPrecio()); // Establecer el precio del primer producto
        }

        model.addAttribute("factura", factura);
        model.addAttribute("clientes", clientes);
        model.addAttribute("productos", productos);
        return "pages/Factura/formularioFactura"; // Ruta de la vista
    }

    // GUARDAR FACTURA
    @PostMapping("/guardarFactura")
    public String guardarFactura(@ModelAttribute("factura") Factura factura) {
        facturaServicio.guardarFactura(factura);
        return "redirect:/facturas"; // Redirige a la lista de facturas
    }

    // EDITAR FACTURA
    @GetMapping("/editar/{id}")
    public String editarFactura(@PathVariable Long id, Model model) {
        Optional<Factura> factura = facturaServicio.buscarFacturaPorId(id);
        List<Cliente> clientes = clienteServicio.listarClientes();
        List<Producto> productos = productoServicio.listarProductos();
        factura.ifPresent(f -> model.addAttribute("factura", f));
        model.addAttribute("clientes", clientes);
        model.addAttribute("productos", productos);
        return "pages/Factura/listaFacturas"; // Ruta de la vista
    }

    // ELIMINAR FACTURA
    @GetMapping("/eliminar/{id}")
    public String eliminarFactura(@PathVariable Long id) {
        facturaServicio.eliminarFactura(id);
        return "redirect:/facturas"; // Redirige a la lista de facturas
    }
}