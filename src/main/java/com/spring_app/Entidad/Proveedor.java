package com.spring_app.Entidad;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String nombre;
    private  String direccion;


    @OneToMany(mappedBy = "proveedor")
    private List<Producto> productos;



}
