package com.spring_app.Servicio;

import com.spring_app.Entidad.Cliente;
import com.spring_app.Repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public List<Cliente> listarClientes() {
        return clienteRepositorio.findAll();
    }

    public List<Cliente> buscarClientePorNombre(String buscarCliente) {
        if (buscarCliente == null || buscarCliente.isEmpty()) {
            return clienteRepositorio.findAll();
        } else {
            return clienteRepositorio.findByNombreContainingIgnoreCase(buscarCliente);
        }
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepositorio.findById(id);
    }

    public void guardarCliente(Cliente cliente) {
        clienteRepositorio.save(cliente);
    }

    public void eliminarCliente(Long id) {
        clienteRepositorio.deleteById(id);
    }
}