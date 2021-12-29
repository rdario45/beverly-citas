package service;

import com.google.inject.Inject;
import repository.ClientaRepository;
import domain.Clienta;

import java.util.List;
import java.util.Optional;

public class ClientasService {

    private ClientaRepository repository;

    @Inject
    public ClientasService(ClientaRepository repository) {
        this.repository = repository;
    }

    public List<Clienta> findAll() {
        return repository.findAll();
    }

    public Clienta registerClient(Clienta client) {
        Clienta saved = repository.save(client);
        return saved;
    }

    public Optional<Clienta> find(Integer id) {
        return repository.find(id);
    }
}
