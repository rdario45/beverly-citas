package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import controllers.acl.AuthAction;
import domain.Client;
import play.libs.Json;
import play.mvc.*;
import service.ClientsService;

import java.util.List;

@With(AuthAction.class)
public class ClientsController extends Controller {

    private ClientsService clientsService;

    @Inject
    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    public Result index(Http.Request request) {
//        request
//            .session()
//            .get("connected")
//            .map(user -> ok("Hello " + user))
//            .orElseGet(() -> unauthorized("Oops, you are not connected"));
        List<Client> allClients = clientsService.consultarTodos();
        return ok(Json.toJson(allClients));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result save(Http.Request request) {
        JsonNode json = request.body().asJson();
        Client client = Json.fromJson(json, Client.class);
        Client registrado = clientsService.registrarCliente(client);
        return ok(Json.toJson(registrado));
    }

}
