package com.spring_app.Servicio;

import com.spring_app.Entidad.Factura;
import com.spring_app.Repositorio.FacturaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServicio {

    @Autowired
    private FacturaRepositorio facturaRepositorio;

    public List<Factura> listarFacturas() {
        return facturaRepositorio.findAll();
    }

    public Optional<Factura> buscarFacturaPorId(Long id) {
        return facturaRepositorio.findById(id);
    }

    public void guardarFactura(Factura factura) {
        facturaRepositorio.save(factura);
    }

    public void eliminarFactura(Long id) {
        facturaRepositorio.deleteById(id);
    }
}