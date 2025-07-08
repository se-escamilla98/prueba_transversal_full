package com.fullstack.veterinaria.Repository;

import com.fullstack.veterinaria.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByRutAndCorreo(String rut, String usuarioCorreo);
}
