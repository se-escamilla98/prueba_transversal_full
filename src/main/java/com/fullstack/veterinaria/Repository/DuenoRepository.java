package com.fullstack.veterinaria.Repository;

import com.fullstack.veterinaria.Model.Consulta;
import com.fullstack.veterinaria.Model.Dueno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DuenoRepository extends JpaRepository<Dueno, Integer> {
    Dueno findByRutAndCorreoElectronico(String Rut, String correo);

}
