package controllers;

import acl.AuthAction;
import acl.types.Attrs;
import acl.types.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import domain.Cita;
import play.libs.Json;
import play.mvc.*;
import service.CitasService;

import java.util.HashMap;

@With(AuthAction.class)
public class CitasController extends Controller {

    private CitasService citasService;

    @Inject
    public CitasController(CitasService citasService) {
        this.citasService = citasService;
    }

    public Result list(Http.Request request) {
        return request.attrs().getOptional(Attrs.USER).map(user ->
                ok(Json.toJson(getAuthorizedResponse(user, citasService.findAll())))
        ).orElse(unauthorized());
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result save(Http.Request request) {
        return request.attrs().getOptional(Attrs.USER).map(user -> {
            JsonNode json = request.body().asJson();
            Cita cita = Json.fromJson(json, Cita.class);
            Cita data = citasService.save(cita);
            return ok(Json.toJson(getAuthorizedResponse(user, data)));
        }).orElse(unauthorized());
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result update(String id, Http.Request request) {
        return request.attrs().getOptional(Attrs.USER).map(user -> {
            JsonNode json = request.body().asJson();
            Cita cita = Json.fromJson(json, Cita.class);
            return citasService.update(cita, id).map(data ->
                    ok(Json.toJson(getAuthorizedResponse(user, data)))
            ).orElse(notFound());
        }).orElse(unauthorized());
    }

    public Result delete(String id, Http.Request request) {
        return request.attrs().getOptional(Attrs.USER).map(user ->
                        citasService.delete(id).map(data ->
                                ok(Json.toJson(getAuthorizedResponse(user, data)))
                        ).orElse(notFound()))
                .orElse(unauthorized());
    }

    private HashMap getAuthorizedResponse(User user, Object data) {
        HashMap response = new HashMap();
        response.put("data", data);
        response.put("user", user);
        return response;
    }

}
