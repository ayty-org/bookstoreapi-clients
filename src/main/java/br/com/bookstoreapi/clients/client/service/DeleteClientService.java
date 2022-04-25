package br.com.bookstoreapi.clients.client.service;

import br.com.bookstoreapi.clients.exception.DeleteException;
import br.com.bookstoreapi.clients.exception.EntityNotFoundException;

import java.util.UUID;

@FunctionalInterface
public interface DeleteClientService {

    void delete(UUID id) throws EntityNotFoundException, DeleteException;
}
