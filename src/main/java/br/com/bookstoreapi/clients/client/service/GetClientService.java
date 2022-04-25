package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;

import java.util.UUID;

@FunctionalInterface
public interface GetClientService {

    Client getByUuid(UUID id) throws EntityNotFoundException;
}
