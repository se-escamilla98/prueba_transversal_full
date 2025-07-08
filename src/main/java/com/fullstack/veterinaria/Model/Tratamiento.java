package com.fullstack.veterinaria.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tratamiento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tratamientoId;

    @Column(length = 100, unique = true, nullable = false)
    private String nombre;

    @Column(length = 255, nullable = false)
    private String descripcion;
}