package service;

import com.google.inject.Inject;
import database.ClientRepository;
import domain.Client;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ClientsService {

    private ClientRepository repository;

    @Inject
    public ClientsService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<Client> consultarTodos() {
        return repository.listAll();
    }

    public Client registrarCliente(Client client) {
        client.setStatus("ACTIVE");
        Client saved = repository.save(client);
        calcDiscounts(saved);
        return saved;
    }

    private List<Client> getAllReferred(Client client) {
        return repository.getAllReferred(client.getId());
    }

    private Client consultarClient(Integer id) {
        return repository.find(id);
    }

    private void calcDiscounts(Client client) {
        if (client.getReferred() != 0 ) {
            Client parent = consultarClient(client.getReferred());
            CompletableFuture.runAsync(()-> {
                double discount = calcDiscount(0, parent);
                parent.setDiscount(discount);
                repository.update(parent);
            });
            calcDiscounts(parent);
        }
    }

    private double calcDiscount(Integer level, Client client) {
        if ( level < 4) { // max 3
            List<Client> referidos = getAllReferred(client);
            if ( referidos.size() > 2 ) { // min 3
                return 0.05 + referidos.stream()
                        .map(c -> calcDiscount(level + 1, c))
                        .reduce(Double::sum).get();
            }
        }
        return 0.0;
    }

}
