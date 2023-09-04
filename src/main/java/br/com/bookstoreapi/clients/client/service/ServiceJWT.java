package br.com.bookstoreapi.clients.client.service;

import br.com.bookstoreapi.clients.client.FilterJWT;
import br.com.bookstoreapi.clients.client.LoginRequestDTO;
import br.com.bookstoreapi.clients.client.LoginResponseDTO;
import br.com.bookstoreapi.clients.exception.EntityNotFoundException;
import io.jsonwebtoken.Jwts;

import static br.com.bookstoreapi.clients.client.service.ServiceJWTImpl.TOKEN_KEY;

public interface ServiceJWT {
    LoginResponseDTO auth(LoginRequestDTO client) throws EntityNotFoundException;

    static String getEmailFromToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new SecurityException("Token inexistente ou mal formatado!");
        }

        // Extraindo apenas o token do cabecalho.
        String token = authorizationHeader.substring(FilterJWT.TOKEN_INDEX);

        String subject = null;
        subject = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
        return subject;
    }
}
