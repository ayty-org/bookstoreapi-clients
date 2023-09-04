package br.com.bookstoreapi.clients.client.service;

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
    public static final String TOKEN_KEY = "wdsjfhkwbfdgwuierhweasd13rasdqeatehbsdhjnwr6iw5ejw56aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaik57kwqrytkw576kll86ij";

    public LoginResponseDTO auth(LoginRequestDTO client) throws EntityNotFoundException {
        if (!getClientService.existByEmailAndPassword(client.getEmail(), client.getPassword())) {
            throw new EntityNotFoundException();
        }

        String token = createToken(client.getEmail());
        return new LoginResponseDTO(token);
    }

    private String createToken(String email) {
        return Jwts.builder().setHeaderParam("typ", "JWT")
                .setSubject(email)
                .signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 10000)).compact();// 30 min
    }
}
