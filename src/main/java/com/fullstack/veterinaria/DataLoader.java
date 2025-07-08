package com.fullstack.veterinaria;

import com.fullstack.veterinaria.Model.*;
import com.fullstack.veterinaria.Repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private DuenoRepository duenoRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) {
        try {
            crearDuenos(5);
            crearUsuarios(5);
            crearTratamientos(5);
            crearMascotas(5);
            crearConsultas(5);
        } catch (Exception e) {
            System.out.println("Error en carga de datos: " + e.getMessage());
        }
    }

    private void crearDuenos(int cantidad) {
        Faker faker = new Faker();
        for (int i = 0; i < cantidad; i++) {
            Dueno dueno = new Dueno();
            String rut = faker.idNumber().valid().replace("-", "").substring(0, 8);
            dueno.setRut(rut);
            dueno.setNombreCompleto(faker.name().fullName());
            dueno.setCorreo(faker.internet().emailAddress()); // ✔️ corregido
            dueno.setTelefono(faker.numerify("#########"));

            duenoRepository.save(dueno);
        }
    }

    private void crearUsuarios(int cantidad) {
        Faker faker = new Faker();
        for (int i = 0; i < cantidad; i++) {
            Usuario usuario = new Usuario();
            String rut = faker.idNumber().valid().replace("-", "").substring(0, 8);
            usuario.setRut(rut);
            usuario.setNombreCompleto(faker.name().fullName());
            usuario.setUsuarioCorreo(faker.internet().emailAddress()); // ✔️ corregido
            usuarioRepository.save(usuario);
        }
    }

    private void crearTratamientos(int cantidad) {
        Faker faker = new Faker();
        for (int i = 0; i < cantidad; i++) {
            Tratamiento tratamiento = new Tratamiento();
            tratamiento.setNombre(faker.name().title());
            tratamiento.setDescripcion(faker.lorem().sentence());
            tratamientoRepository.save(tratamiento);
        }
    }

    private void crearMascotas(int cantidad) {
        Faker faker = new Faker();
        List<Dueno> duenos = duenoRepository.findAll();
        if (duenos.isEmpty()) return;

        for (int i = 0; i < cantidad; i++) {
            Mascota mascota = new Mascota();
            mascota.setNombre(faker.animal().name());
            mascota.setEspecie(faker.animal().species());
            mascota.setRaza(faker.animal().genus());
            mascota.setEdad(faker.number().numberBetween(1, 15));
            mascota.setDueno(duenos.get(i % duenos.size()));
            mascotaRepository.save(mascota);
        }
    }

    private void crearConsultas(int cantidad) {
        Faker faker = new Faker();
        List<Mascota> mascotas = mascotaRepository.findAll();
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Tratamiento> tratamientos = tratamientoRepository.findAll();

        if (mascotas.isEmpty() || usuarios.isEmpty() || tratamientos.isEmpty()) {
            System.out.println("No se pueden crear consultas porque faltan datos relacionados.");
            return;
        }

        for (int i = 0; i < cantidad; i++) {
            Consulta consulta = new Consulta();
            consulta.setFechaConsulta(generarFechaAleatoria());
            consulta.setObservaciones(faker.lorem().sentence());
            consulta.setMascota(mascotas.get(i % mascotas.size()));
            consulta.setUsuario(usuarios.get(i % usuarios.size()));
            consulta.setTratamiento(tratamientos.get(i % tratamientos.size()));
            consultaRepository.save(consulta);
        }
    }

    private Date generarFechaAleatoria() {
        long milisegundosEnUnDia = 24L * 60 * 60 * 1000;
        long diasPasados = (long) (Math.random() * 3650); // hasta 10 años atrás
        return new Date(System.currentTimeMillis() - (diasPasados * milisegundosEnUnDia));
    }
}
