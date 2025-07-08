package com.fullstack.veterinaria.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dueno")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dueno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer duenoId;

    @Column(length = 12, nullable = false, unique = true)
    private String rut;

    @Column(length = 100, nullable = false)
    private String nombreCompleto;

    @Column(length = 100, nullable = false, unique = true)
    private String correo;

    @Column(length = 15, nullable = false)
    private String telefono;
}
