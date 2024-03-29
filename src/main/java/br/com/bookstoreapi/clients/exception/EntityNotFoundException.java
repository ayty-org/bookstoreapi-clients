package br.com.bookstoreapi.clients.exception;

import java.util.UUID;

public class EntityNotFoundException extends Exception{

    public EntityNotFoundException(UUID id, String className){
        super(className+" with id "+id+" not found");
    }

    public EntityNotFoundException(){
        super("Cliente não encontrado, não foi possível fazer login, verifique o email e a senha e tente novamente");
    }
}
