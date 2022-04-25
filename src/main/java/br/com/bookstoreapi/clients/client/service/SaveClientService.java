package br.com.bookstoreapi.clients.client.service;


import br.com.bookstoreapi.clients.client.Client;

@FunctionalInterface
public interface SaveClientService {

    Client save(Client client);
}
