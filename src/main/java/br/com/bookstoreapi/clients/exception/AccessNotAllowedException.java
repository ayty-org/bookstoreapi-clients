package br.com.bookstoreapi.clients.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccessNotAllowedException extends Exception{

    public AccessNotAllowedException(){
        super("You don't have authorization to get this user");
    }
}
