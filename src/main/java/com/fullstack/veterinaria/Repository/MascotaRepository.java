package com.fullstack.veterinaria.Repository;

import com.fullstack.veterinaria.Model.Mascota;
import com.fullstack.veterinaria.Model.Usuario;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
    Mascota findByEspecieAndRaza(String especie, String raza);
    List<Mascota> findByDuenoDuenoId(Integer duenoId);

}
