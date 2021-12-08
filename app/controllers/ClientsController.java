package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import controllers.acl.types.Attrs;
import controllers.acl.AuthAction;
import controllers.acl.types.User;
import domain.Client;
import play.libs.Json;
import play.mvc.*;
import service.ClientsService;

import java.util.HashMap;

@With(AuthAction.class)
public class ClientsController extends Controller {

    private ClientsService clientsService;

    @Inject
    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    public Result index(Http.Request request) {
        return request.attrs().getOptional(Attrs.USER)
                .map(user -> ok(Json.toJson(getAuthorizedResponse(user, clientsService.consultarTodos()))))
                .orElse(unauthorized());
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result save(Http.Request request) {
        return request.attrs().getOptional(Attrs.USER)
                .map(user -> {
                    JsonNode json = request.body().asJson();
                    Client client = Json.fromJson(json, Client.class);
                    Client data = clientsService.registrarCliente(client);
                    return ok(Json.toJson(getAuthorizedResponse(user, data)));
                }).orElse(unauthorized());
    }

    private HashMap getAuthorizedResponse(User user, Object data) {
        HashMap response = new HashMap();
        response.put("data", data);
        response.put("user", user);
        return response;
    }

}
