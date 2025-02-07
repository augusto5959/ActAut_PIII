package com.spring_app.Controlador;

import com.spring_app.Entidad.Producto;
import com.spring_app.Servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductoControlador {

    @Autowired
    ProductoServicio productoServicio;

    // Ruta raíz para cargar el index.html
    @GetMapping("/")
    public String mostrarIndex() {
        return "pages/index"; // Devuelve la vista index.html
    }

    //LEER
    @GetMapping("/productos")
    public String mostrarProductos(@RequestParam(name = "buscarProducto", required = false, defaultValue = "") String buscarProducto, Model model){
        List<Producto> productos = productoServicio.buscarProductoNombre(buscarProducto);
        model.addAttribute("buscarProducto", buscarProducto);
        model.addAttribute("productos", productos);
        return "pages/Producto/listaProductos";
    }

    //CREAR
    @GetMapping("/formulario")
    public String formularioProducto(Model model){
        model.addAttribute("producto", new Producto());
        return "pages/Producto/formulario";
    }

    @PostMapping("/guardar")
    public String crearProducto(Producto producto){
        productoServicio.guardarProducto(producto);
        return "redirect:/productos";
    }

    //ACTUALIZAR
    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable Long id, Model model){
        Optional<Producto> producto = productoServicio.buscarProducto(id);
        model.addAttribute("producto", producto);
        return "pages/Producto/formulario";
    }

    //ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id){
        productoServicio.eliminarProducto(id);
        return "redirect:/productos";
    }

    @GetMapping("/productos/pdf")
    public ResponseEntity<byte[]> descargaPdf() throws Exception{
        String rutaPdf = productoServicio.generarPdf();
        File pdfFile = new File(rutaPdf);
        if (!pdfFile.exists()){
            throw new FileNotFoundException("El archivo pdf no existe");
        }
        byte[] contenido = Files.readAllBytes(pdfFile.toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment","productos.pdf");
        return new ResponseEntity<>(contenido, headers, HttpStatus.OK);
    }
}