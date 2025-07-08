package com.fullstack.veterinaria.Controller;

import com.fullstack.veterinaria.Model.Mascota;
import com.fullstack.veterinaria.Model.Usuario;
import com.fullstack.veterinaria.Service.MascotaService;
import com.fullstack.veterinaria.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    @Autowired
    public UsuarioService usuarioService;

    @GetMapping("/")
    public ResponseEntity<?> listarMascota() {
        return ResponseEntity.ok(usuarioService.listarUsuario());
    }
    @GetMapping("/usuario/{rut}/{correo}")
    public ResponseEntity<?> findByRutAndCorreo(
            @PathVariable("rut") String rut,
            @PathVariable("correo") String usuarioCorreo) {

        Usuario usuario = usuarioService.findByRutAndCorreo(rut, usuarioCorreo);

        if (usuario == null) {
            return ResponseEntity.status(404).body("Dueño no encontrado");
        }

        return ResponseEntity.ok(usuario);
    }
}
