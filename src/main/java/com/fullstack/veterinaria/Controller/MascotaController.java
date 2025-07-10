package com.fullstack.veterinaria.Controller;

import com.fullstack.veterinaria.Model.Mascota;
import com.fullstack.veterinaria.Service.MascotaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mascota")
@Tag(name = "Servicios de mascota", description = "CRUD de Mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    // GET - Listar todas las mascotas
    @GetMapping
    public ResponseEntity<?> listarMascota() {
        return ResponseEntity.ok(mascotaService.listarMascota());
    }

    // GET - Buscar por especie y raza
    @GetMapping("/{especie}/{raza}")
    public ResponseEntity<?> findByEspecieAndRaza(
            @PathVariable("especie") String especie,
            @PathVariable("raza") String raza) {

        Mascota mascota = mascotaService.findByEspecieAndRaza(especie, raza);
        if (mascota == null) {
            return ResponseEntity.status(404).body("Mascota no encontrada");
        }
        return ResponseEntity.ok(mascota);
    }

    // POST - Registrar nueva mascota
    @PostMapping
    public ResponseEntity<?> registrarMascota(@RequestBody Mascota mascota) {
        if (mascota == null || mascota.getDueno() == null) {
            return ResponseEntity.badRequest().body("Datos inválidos");
        }
        Mascota nueva = mascotaService.registrarMascota(mascota);
        return ResponseEntity.ok(nueva);
    }

    // PUT - Actualizar completamente una mascota
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarMascota(
            @PathVariable("id") Integer id,
            @RequestBody Mascota nuevaMascota) {

        if (nuevaMascota == null || nuevaMascota.getDueno() == null) {
            return ResponseEntity.badRequest().body("Datos inválidos");
        }

        Mascota actualizada = mascotaService.actualizarMascota(id, nuevaMascota);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // PATCH - Actualización  de mascota
    @PatchMapping("/{id}")
    public ResponseEntity<?> actualizarMascotaParcial(
            @PathVariable("id") Integer id,
            @RequestBody Mascota datosParciales) {

        if (datosParciales == null) {
            return ResponseEntity.badRequest().body("Datos inválidos");
        }

        Mascota actualizada = mascotaService.actualizarMascotaParcial(id, datosParciales);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //DELETE - BORRAR MASCOTA POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable("id") Integer id) {
        boolean eliminado = mascotaService.eliminarMascota(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
