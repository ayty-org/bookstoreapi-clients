package br.com.bookstoreapi.clients.client.service;

import br.com.bookstoreapi.clients.client.LoginRequestDTO;
import br.com.bookstoreapi.clients.client.LoginResponseDTO;
import br.com.bookstoreapi.clients.exception.EntityNotFoundException;

public interface ServiceJWT {
    LoginResponseDTO auth(LoginRequestDTO client) throws EntityNotFoundException;

    String verifyToken(String authorizationHeader);
}
