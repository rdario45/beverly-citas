package controllers;

import acl.AuthAction;
import acl.types.Attrs;
import acl.types.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
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

    public Result list(Http.Request request) {
        return request.attrs().getOptional(Attrs.USER)
                .map(user -> ok(Json.toJson(getAuthorizedResponse(user, clientsService.findAll()))))
                .orElse(unauthorized());
    }

    public Result find(Integer id, Http.Request request) {
        return  request.attrs().getOptional(Attrs.USER)
                .map(user -> clientsService.find(id)
                        .map(client ->
                                ok(Json.toJson(getAuthorizedResponse(user, client)))
                        ).orElse(notFound())
                ).orElse(unauthorized());
    }


    @BodyParser.Of(BodyParser.Json.class)
    public Result save(Http.Request request) {
        return request.attrs().getOptional(Attrs.USER)
                .map(user -> {
                    JsonNode json = request.body().asJson();
                    Client client = Json.fromJson(json, Client.class);
                    Client data = clientsService.registerClient(client);
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
