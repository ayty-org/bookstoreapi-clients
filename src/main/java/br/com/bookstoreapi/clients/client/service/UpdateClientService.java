package br.com.bookstoreapi.clients.client.service;


import br.com.bookstoreapi.clients.client.Client;
import br.com.bookstoreapi.clients.exception.AccessNotAllowedException;
import br.com.bookstoreapi.clients.exception.EntityNotFoundException;

import java.util.UUID;

@FunctionalInterface
public interface UpdateClientService {

    Client update(UUID id, Client client, String bearerToken) throws EntityNotFoundException, AccessNotAllowedException;
}
