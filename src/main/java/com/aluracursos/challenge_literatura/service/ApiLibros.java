package com.aluracursos.challenge_literatura.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiLibros {
    private static final String BASE_URL = "https://gutendex.com/books/?search=";

    public static String buscarLibros(String busqueda) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create(BASE_URL + busqueda)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            System.out.println("No se encontró el libro: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}