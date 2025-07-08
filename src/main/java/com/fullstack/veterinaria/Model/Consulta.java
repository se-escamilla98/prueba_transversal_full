package com.fullstack.veterinaria.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "consulta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer consultaId;

    @Column(nullable = false,name = "fecha_consulta")
    private Date fechaConsulta;

    @Column(length = 255, nullable = false)
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "consulta-mascota", nullable = false)
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "consulta-tratamiento", nullable = false)
    private Tratamiento tratamiento;

    @ManyToOne
    @JoinColumn(name = "consulta-usuario", nullable = false)
    private Usuario usuario;
}
