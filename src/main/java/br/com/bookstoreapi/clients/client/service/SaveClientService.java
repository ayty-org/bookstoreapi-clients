package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;

@FunctionalInterface
public interface SaveClientService {

    Client save(Client client);
}
