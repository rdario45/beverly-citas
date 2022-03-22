package service;

import acl.BeverlySNS;
import com.google.inject.Inject;
import domain.Cita;
import play.libs.Json;
import repository.CitaRepository;
import sqs.events.CitaActualizada;
import sqs.events.CitaCreada;
import sqs.events.CitaEliminada;

import java.util.Optional;
import java.util.UUID;

public class CitasService {

    private CitaRepository repository;

    @Inject
    public CitasService(CitaRepository repository) {
        this.repository = repository;
    }

    public Cita save(Cita cita) {
        cita.setId(UUID.randomUUID().toString());
        CitaCreada citaCreada = new CitaCreada(cita);
        String message = Json.toJson(citaCreada).toString();
        BeverlySNS.send(message);
        return repository.save(cita);
    }

    public Optional<Cita> update(Cita cita, String id) {
        return repository.findFirst(id).map(found -> {
            cita.setId(found.getId());
            CitaActualizada citaActualizada = new CitaActualizada(cita);
            BeverlySNS.send(Json.toJson(citaActualizada).toString());
            repository.remove(id, found.getHora());
            return repository.save(cita);
        });
    }

    public Optional<Cita> delete(String id) {
        return repository.findFirst(id).map(cita -> {
            CitaEliminada citaEliminada = new CitaEliminada(cita);
            repository.remove(id, cita.getHora());
            BeverlySNS.send(Json.toJson(citaEliminada).toString());
            return cita;
        });
    }
}