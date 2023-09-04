package br.com.bookstoreapi.clients.client.service;

import br.com.bookstoreapi.clients.client.Client;
import br.com.bookstoreapi.clients.exception.AccessNotAllowedException;
import br.com.bookstoreapi.clients.exception.EntityNotFoundException;
import org.apache.http.auth.InvalidCredentialsException;

import java.util.UUID;

public interface GetClientService {

    Client getByUuid(UUID id, String bearerToken) throws EntityNotFoundException, AccessNotAllowedException;
    Boolean existByEmailAndPassword(String email, String password) throws EntityNotFoundException;
}
