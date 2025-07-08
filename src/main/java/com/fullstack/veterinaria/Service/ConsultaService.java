package com.fullstack.veterinaria.Service;

import com.fullstack.veterinaria.Model.Consulta;
import com.fullstack.veterinaria.Model.Mascota;
import com.fullstack.veterinaria.Repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Consulta> listarConsulta() {
        return consultaRepository.findAll();
    }

    public Consulta registrarConsulta(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public void eliminarConsulta(Integer id) {
        consultaRepository.deleteById(id);
    }

    public Consulta buscarPorFechaYmascota(Date fechaConsulta, Mascota mascota) {
        return consultaRepository.buscarPorFechaYmascota(fechaConsulta, mascota);
    }
}
