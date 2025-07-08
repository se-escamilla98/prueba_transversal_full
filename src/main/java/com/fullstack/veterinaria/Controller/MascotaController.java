package com.fullstack.veterinaria.Controller;

import com.fullstack.veterinaria.Model.Dueno;
import com.fullstack.veterinaria.Model.Mascota;
import com.fullstack.veterinaria.Service.DuenoService;
import com.fullstack.veterinaria.Service.MascotaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mascota")
@Tag(name = "Servicios de mascota", description = "")
public class MascotaController {
    @Autowired
    public MascotaService mascotaService;

    @GetMapping("/")
    public ResponseEntity<?> listarMascota() {
        return ResponseEntity.ok(mascotaService.listarMascota());
    }
    @GetMapping("/mascota/{especie}/{raza}")
    public ResponseEntity<?> findByEspecieAndRaza(
            @PathVariable("especie") String especie,
            @PathVariable("raza") String raza) {

        Mascota mascota = mascotaService.findByEspecieAndRaza(especie, raza);

        if (mascota == null) {
            return ResponseEntity.status(404).body("Dueño no encontrado");
        }

        return ResponseEntity.ok(mascota);
    }
}
