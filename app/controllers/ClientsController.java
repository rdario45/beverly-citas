package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import domain.Client;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.ClientsService;

import java.util.List;

public class ClientsController extends Controller {

    private ClientsService clientsService;

    @Inject
    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    public Result index() {
        List<Client> allClients = clientsService.consultarTodos();
        return ok(Json.toJson(allClients));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result save(Http.Request request) {
        JsonNode json = request.body().asJson();
        Client client = Json.fromJson(json, Client.class);
        clientsService.registrarCliente(client);
        return ok(Json.toJson(client));
    }

}
