package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.exception.DeleteException;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;

import java.util.UUID;

@FunctionalInterface
public interface DeleteClientService {

    void delete(UUID id) throws EntityNotFoundException, DeleteException;
}
