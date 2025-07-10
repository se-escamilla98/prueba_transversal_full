package com.fullstack.veterinaria.Service;

import com.fullstack.veterinaria.Model.Dueno;
import com.fullstack.veterinaria.Model.Mascota;
import com.fullstack.veterinaria.Repository.DuenoRepository;
import com.fullstack.veterinaria.Repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DuenoService {

    @Autowired
    private DuenoRepository duenoRepository;

    public List<Dueno> listarDueno() {
        return duenoRepository.findAll();
    }

    public Dueno registrarDueno(Dueno dueno){
        return duenoRepository.save(dueno);
    }

    @Autowired
    private MascotaRepository mascotaRepository;

    // METODO ELIMINAR DUENO
    public boolean eliminarDueno(Integer duenoId) {
        if (!duenoRepository.existsById(duenoId)) {
            System.out.println("Dueño no encontrado.");
            return false;
        }

        List<Mascota> mascotasAsociadas = mascotaRepository.findByDuenoDuenoId(duenoId);
        if (!mascotasAsociadas.isEmpty()) {
            System.out.println("No se puede eliminar el dueño. Tiene mascotas asociadas.");
            return false;
        }

        duenoRepository.deleteById(duenoId);
        return true;
    }


    //METODO LISTAR A TODOS LOS DUENOS
    public Dueno getDuenoById(Integer id) {
        return duenoRepository.findById(id).orElse(null);
    }

    //METODO ACTUALIZAR DUENO
    public Dueno actualizarDueno(Integer id, Dueno nuevo) {
        return duenoRepository.findById(id).map(dueno -> {
            dueno.setRut(nuevo.getRut());
            dueno.setCorreo(nuevo.getCorreo());
            dueno.setTelefono(nuevo.getTelefono());
            dueno.setNombreCompleto(nuevo.getNombreCompleto());
            return duenoRepository.save(dueno);
        }).orElse(null);
    }

    //METODO DE ACTUALIZACION PARCIAL
    public Dueno actualizarDuenoParcial(Integer id, Dueno parcial) {
        return duenoRepository.findById(id).map(dueno -> {
            if (parcial.getRut() != null) dueno.setRut(parcial.getRut());
            if (parcial.getCorreo() != null) dueno.setCorreo(parcial.getCorreo());
            if (parcial.getTelefono() != null) dueno.setTelefono(parcial.getTelefono());
            if (parcial.getNombreCompleto() != null) dueno.setNombreCompleto(parcial.getNombreCompleto());
            return duenoRepository.save(dueno);
        }).orElse(null);
    }

    //METODO ENCONTRAR POR RUT Y EMAIL
    public Dueno findByRutAndEmail(String rut, String email) {
        return duenoRepository.findByRutAndCorreo(rut, email);
    }
}
