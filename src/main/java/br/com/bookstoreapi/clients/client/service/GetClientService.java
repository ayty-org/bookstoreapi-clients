package br.com.bookstoreapi.clients.client.service;

import br.com.bookstoreapi.clients.client.Client;
import br.com.bookstoreapi.clients.exception.EntityNotFoundException;

import java.util.UUID;

@FunctionalInterface
public interface GetClientService {

    Client getByUuid(UUID id) throws EntityNotFoundException;
}
