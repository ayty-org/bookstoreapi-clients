package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;

import java.util.UUID;

@FunctionalInterface
public interface UpdateClientService {

    Client update(UUID id, Client client) throws EntityNotFoundException;
}
