package br.com.bookstoreapi.clients.client.service;

import br.com.bookstoreapi.clients.client.FilterJWT;
import br.com.bookstoreapi.clients.client.LoginRequestDTO;
import br.com.bookstoreapi.clients.client.LoginResponseDTO;
import br.com.bookstoreapi.clients.exception.EntityNotFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ServiceJWTImpl implements ServiceJWT {

    @Autowired
    private GetClientService getClientService;
    public static final String TOKEN_KEY = "wdsjfhkwbfdgwuierhweij";

    public LoginResponseDTO auth(LoginRequestDTO client) throws EntityNotFoundException {
        if (!getClientService.existByEmailAndPasswrd(client.getEmail(), client.getPassword())) {
            throw new EntityNotFoundException();
        }

        String token = createToken(client.getEmail());
        return new LoginResponseDTO(token);
    }

    private String createToken(String email) {
        return Jwts.builder().setHeaderParam("typ", "JWT")
                .setSubject(email)
                .signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 1000)).compact();// 3 min
    }

    public String verifyToken(String authorizationHeader) {
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
