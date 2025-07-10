package com.fullstack.veterinaria.Controller;

import com.fullstack.veterinaria.Model.Dueno;
import com.fullstack.veterinaria.Service.DuenoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dueno")
@Tag(name = "Servicios de dueños", description = "")
public class DuenoController {

    @Autowired
    private DuenoService duenoService;

    // ✅ LISTAR TODOS LOS DUEÑOS
    @GetMapping
    public ResponseEntity<?> listarDueno() {
        return ResponseEntity.ok(duenoService.listarDueno());
    }

    // ✅ CREAR UN NUEVO DUEÑO
    @PostMapping
    public ResponseEntity<Dueno> crearDueno(@RequestBody Dueno dueno) {
        return ResponseEntity.ok(duenoService.registrarDueno(dueno));
    }

    // ✅ OBTENER DUEÑO POR ID (CORREGIDO)
    @GetMapping("/{id}")
    public ResponseEntity<Dueno> obtenerDueno(@PathVariable Integer id) {
        Dueno dueno = duenoService.getDuenoById(id);
        if (dueno != null) return ResponseEntity.ok(dueno);
        return ResponseEntity.notFound().build();
    }

    // ✅ ELIMINAR DUEÑO
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDueno(@PathVariable Integer id) {
        boolean eliminado = duenoService.eliminarDueno(id);
        if (eliminado) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.status(409).body("No se puede eliminar el dueño porque tiene mascotas asociadas o no existe.");
        }
    }

    // ✅ ACTUALIZACIÓN COMPLETA (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Dueno> actualizarDueno(@PathVariable Integer id, @RequestBody Dueno nuevo) {
        Dueno actualizado = duenoService.actualizarDueno(id, nuevo);
        if (actualizado != null) return ResponseEntity.ok(actualizado);
        return ResponseEntity.notFound().build();
    }

    // ✅ ACTUALIZACIÓN PARCIAL (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<Dueno> actualizarDuenoParcial(@PathVariable Integer id, @RequestBody Dueno parcial) {
        Dueno actualizado = duenoService.actualizarDuenoParcial(id, parcial);
        if (actualizado != null) return ResponseEntity.ok(actualizado);
        return ResponseEntity.notFound().build();
    }

    // ✅ BUSCAR POR RUT Y CORREO
    @GetMapping("/buscar/{rut}/{email}")
    public ResponseEntity<?> findByRutAndEmail(
            @PathVariable("rut") String rut,
            @PathVariable("email") String email) {

        Dueno dueno = duenoService.findByRutAndEmail(rut, email);

        if (dueno == null) {
            return ResponseEntity.status(404).body("Dueño no encontrado");
        }

        return ResponseEntity.ok(dueno);
    }
}
