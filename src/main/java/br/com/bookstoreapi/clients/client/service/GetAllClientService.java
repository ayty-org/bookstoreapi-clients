package br.com.bookstoreapi.clients.client.service;


import br.com.bookstoreapi.clients.client.Client;

import java.util.List;

@FunctionalInterface
public interface GetAllClientService {

    List<Client> findAll();
}
