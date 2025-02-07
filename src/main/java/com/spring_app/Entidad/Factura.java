package com.spring_app.Entidad;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;
    private Double precio;
    private Double subtotal;
    private Double total;


    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;


    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

}
