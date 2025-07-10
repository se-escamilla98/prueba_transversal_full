package com.fullstack.veterinaria.Controller;

import com.fullstack.veterinaria.Model.Consulta;
import com.fullstack.veterinaria.Model.Mascota;
import com.fullstack.veterinaria.Repository.MascotaRepository;
import com.fullstack.veterinaria.Service.ConsultaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/consultas")
@Tag(name = "Servicios de Consultas", description = "Operaciones relacionadas con consultas veterinarias")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private MascotaRepository mascotaRepository;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarConsulta(@PathVariable Integer id) {
        try {
            consultaService.eliminarConsulta(id);
            return ResponseEntity.ok("Consulta eliminada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Consulta no encontrada");
        }
    }


    @GetMapping
    public ResponseEntity<?> listarConsultas() {
        return ResponseEntity.ok(consultaService.listarConsulta());
    }

    @GetMapping("/{fechaConsulta}/{id}")
    public ResponseEntity<?> findByDateAndIdMascota(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaConsulta,
            @PathVariable("id") Integer mascotaId) {

        Mascota mascota = mascotaRepository.findById(mascotaId).orElse(null);

        if (mascota == null) {
            return ResponseEntity.status(404).body("Mascota no encontrada");
        }

        Consulta consulta = consultaService.buscarPorFechaYmascota(fechaConsulta, mascota);

        if (consulta == null) {
            return ResponseEntity.status(404).body("Consulta no encontrada");
        }

        return ResponseEntity.ok(consulta);
    }
}
