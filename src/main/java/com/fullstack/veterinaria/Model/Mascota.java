package com.fullstack.veterinaria.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mascota")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mascotaId;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 30, nullable = false)
    private String especie;

    @Column(length = 30, nullable = false)
    private String raza;

    @Column(nullable = false)
    private Integer edad;

    @ManyToOne
    @JoinColumn(name = "id-dueno", nullable = false)
    private Dueno dueno;
}
