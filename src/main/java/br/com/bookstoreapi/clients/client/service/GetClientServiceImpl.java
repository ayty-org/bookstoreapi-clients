package br.com.bookstoreapi.clients.client.service;

import br.com.bookstoreapi.clients.client.Client;
import br.com.bookstoreapi.clients.client.ClientRepository;
import br.com.bookstoreapi.clients.exception.AccessNotAllowedException;
import br.com.bookstoreapi.clients.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static br.com.bookstoreapi.clients.client.service.ServiceJWT.getEmailFromToken;

@Service
@RequiredArgsConstructor
public class GetClientServiceImpl implements GetClientService{

    private final ClientRepository clientRepository;

    @Override
    public Client getByUuid(UUID uuid, String bearerToken) throws EntityNotFoundException, AccessNotAllowedException {
        Client client = clientRepository.findByUuid(uuid)
                .orElseThrow(()-> new EntityNotFoundException(uuid, Client.class.getSimpleName()));
        if(getEmailFromToken(bearerToken).equals(client.getEmail())) {
            return client;
        }
        throw new AccessNotAllowedException();
    }

    @Override
    public Boolean existByEmailAndPassword(String email, String password){
        return clientRepository.existsByEmailAndPassword(email, password);
    }
}
