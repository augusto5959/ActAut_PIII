package com.spring_app.Entidad;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    private String descripcion;
    private Double precio;
    private int stock;


    @ManyToOne
    @JoinColumn(name = "codigo_proveedor")
    private Proveedor proveedor;



}


