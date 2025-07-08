package com.fullstack.veterinaria.Controller;

import com.fullstack.veterinaria.Model.Consulta;
import com.fullstack.veterinaria.Model.Dueno;
import com.fullstack.veterinaria.Service.ConsultaService;
import com.fullstack.veterinaria.Service.DuenoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/dueno")
@Tag(name = "Servicios de dueños", description = "")
public class DuenoController {
    @Autowired
    public DuenoService duenoService;

    @GetMapping("/")
    public ResponseEntity<?> listarConsultas() {
        return ResponseEntity.ok(duenoService.listarDueno());
    }
    @GetMapping("/dueno/{rut}/{email}")
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
