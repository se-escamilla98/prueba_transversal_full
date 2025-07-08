package com.fullstack.veterinaria.Repository;

import com.fullstack.veterinaria.Model.Consulta;
import com.fullstack.veterinaria.Model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

    @Query("SELECT c FROM Consulta c WHERE c.fechaConsulta = :fechaConsulta AND c.mascota = :mascota")
    Consulta buscarPorFechaYmascota(@Param("fechaConsulta") Date fechaConsulta,
                                    @Param("mascota") Mascota mascota);
}
