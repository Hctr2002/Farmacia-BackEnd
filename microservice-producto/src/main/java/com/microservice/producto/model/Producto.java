package com.microservice.producto.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = true)
    private String descripcion;

    @Column(nullable = false)
    private Float precio;

    @Column(nullable = false)
    private Integer stock;

}
