package com.fullstack.veterinaria.Service;

import com.fullstack.veterinaria.Model.Dueno;
import com.fullstack.veterinaria.Model.Mascota;
import com.fullstack.veterinaria.Repository.DuenoRepository;
import com.fullstack.veterinaria.Repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MascotaService {
    @Autowired
    private MascotaRepository mascotaRepository;
    public List<Mascota> listarMascota() {
        return mascotaRepository.findAll();
    }
    public Mascota registrarMascota(Mascota mascota){
        return mascotaRepository.save(mascota);
    }
    public void eliminarMascota(Integer Id){
        mascotaRepository.deleteById(Id);
    }
    public Mascota findByEspecieAndRaza(String especie, String raza) {
        return mascotaRepository.findByEspecieAndRaza(especie, raza);
    }
}
