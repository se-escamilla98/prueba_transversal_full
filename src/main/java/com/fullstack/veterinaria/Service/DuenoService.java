package com.fullstack.veterinaria.Service;

import com.fullstack.veterinaria.Controller.DuenoController;
import com.fullstack.veterinaria.Model.Consulta;
import com.fullstack.veterinaria.Model.Dueno;
import com.fullstack.veterinaria.Repository.DuenoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public void eliminarDueno(Integer Id){
        duenoRepository.deleteById(Id);
    }
    public Dueno findByRutAndEmail(String rut, String email) {
        return duenoRepository.findByRutAndCorreo(rut, email);
    }
}
