package com.fullstack.veterinaria.Service;

import com.fullstack.veterinaria.Model.Mascota;
import com.fullstack.veterinaria.Repository.ConsultaRepository;
import com.fullstack.veterinaria.Repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    // GET: Listar todas las mascotas
    public List<Mascota> listarMascota() {
        return mascotaRepository.findAll();
    }

    // GET: Buscar mascota por especie y raza
    public Mascota findByEspecieAndRaza(String especie, String raza) {
        return mascotaRepository.findByEspecieAndRaza(especie, raza);
    }

    // POST: Registrar nueva mascota
    public Mascota registrarMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }



    public boolean eliminarMascota(Integer mascotaId) {
        try {
            if (!mascotaRepository.existsById(mascotaId)) {
                return false;
            }

            if (!consultaRepository.findByMascota_MascotaId(mascotaId).isEmpty()) {
                System.out.println("No se puede eliminar: la mascota tiene consultas asociadas");
                return false;
            }

            mascotaRepository.deleteById(mascotaId);
            return true;

        } catch (Exception e) {
            System.out.println("Error al eliminar mascota: " + e.getMessage());
            return false;
        }
    }


    // PATCH: Actualizar parcialmente una mascota
    public Mascota actualizarMascotaParcial(Integer id, Mascota datosParciales) {
        return mascotaRepository.findById(id).map(mascotaExistente -> {
            if (datosParciales.getNombre() != null) {
                mascotaExistente.setNombre(datosParciales.getNombre());
            }
            if (datosParciales.getEspecie() != null) {
                mascotaExistente.setEspecie(datosParciales.getEspecie());
            }
            if (datosParciales.getRaza() != null) {
                mascotaExistente.setRaza(datosParciales.getRaza());
            }
            if (datosParciales.getEdad() != null) {
                mascotaExistente.setEdad(datosParciales.getEdad());
            }
            if (datosParciales.getDueno() != null) {
                mascotaExistente.setDueno(datosParciales.getDueno());
            }
            return mascotaRepository.save(mascotaExistente);
        }).orElse(null);
    }


    // PUT: Actualizar completamente una mascota
    public Mascota actualizarMascota(Integer id, Mascota nuevaMascota) {
        return mascotaRepository.findById(id).map(mascotaExistente -> {
            mascotaExistente.setNombre(nuevaMascota.getNombre());
            mascotaExistente.setEspecie(nuevaMascota.getEspecie());
            mascotaExistente.setRaza(nuevaMascota.getRaza());
            mascotaExistente.setEdad(nuevaMascota.getEdad());
            mascotaExistente.setDueno(nuevaMascota.getDueno());
            return mascotaRepository.save(mascotaExistente);
        }).orElse(null);
    }

}
