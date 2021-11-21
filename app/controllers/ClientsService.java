package controllers;

import database.sql.mistery.Client;

import java.util.List;

public class ClientsService {

    public void consultarTodos() {

        new Client("Ana Maria").save();

    }
}
