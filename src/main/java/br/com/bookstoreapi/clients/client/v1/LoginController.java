package br.com.bookstoreapi.clients.client.v1;

import br.com.bookstoreapi.clients.client.LoginRequestDTO;
import br.com.bookstoreapi.clients.client.LoginResponseDTO;
import br.com.bookstoreapi.clients.client.service.ServiceJWT;
import br.com.bookstoreapi.clients.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/v1/")
public class LoginController {

    @Autowired
    private ServiceJWT serviceJWT;

    @PostMapping("auth/login")
    public ResponseEntity<LoginResponseDTO> auth(@RequestBody LoginRequestDTO client) throws EntityNotFoundException {
        return new ResponseEntity<LoginResponseDTO>(serviceJWT.auth(client), HttpStatus.OK);
    }
}
