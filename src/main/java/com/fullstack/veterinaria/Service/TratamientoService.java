package com.fullstack.veterinaria.Service;

import com.fullstack.veterinaria.Model.Mascota;
import com.fullstack.veterinaria.Model.Tratamiento;
import com.fullstack.veterinaria.Repository.MascotaRepository;
import com.fullstack.veterinaria.Repository.TratamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TratamientoService {
    @Autowired
    private TratamientoRepository tratamientoRepository;
    public List<Tratamiento> listarTratamiento() {
        return tratamientoRepository.findAll();
    }
    public Tratamiento registrarTratamiento(Tratamiento tratamiento){
        return tratamientoRepository.save(tratamiento);
    }
    public void eliminarTratamiento(Integer Id){
        tratamientoRepository.deleteById(Id);
    }

}
