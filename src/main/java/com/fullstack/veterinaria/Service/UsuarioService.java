package com.fullstack.veterinaria.Service;

import com.fullstack.veterinaria.Model.Mascota;
import com.fullstack.veterinaria.Model.Usuario;
import com.fullstack.veterinaria.Repository.MascotaRepository;
import com.fullstack.veterinaria.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    public List<Usuario> listarUsuario() {
        return usuarioRepository.findAll();
    }
    public Usuario registrarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    public void eliminarMascota(Integer Id){
        usuarioRepository.deleteById(Id);
    }
    public Usuario findByRutAndCorreo(String rut, String usuarioCorreo) {
        return usuarioRepository.findByRutAndCorreo(rut, usuarioCorreo);
    }
}
