package com.fullstack.veterinaria.Controller;


import com.fullstack.veterinaria.Service.TratamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tratamientos")
public class TratamientoController {
    @Autowired
    public TratamientoService tratamientoService;

    @GetMapping("/")
    public ResponseEntity<?> listarTratamiento() {
        return ResponseEntity.ok(tratamientoService.listarTratamiento());
    }
}
