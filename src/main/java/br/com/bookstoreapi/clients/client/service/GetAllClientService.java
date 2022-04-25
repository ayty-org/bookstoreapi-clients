package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;

import java.util.List;

@FunctionalInterface
public interface GetAllClientService {

    List<Client> findAll();
}
