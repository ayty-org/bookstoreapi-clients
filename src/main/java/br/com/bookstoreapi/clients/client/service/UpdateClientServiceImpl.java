package br.com.bookstoreapi.clients.client.service;

import br.com.bookstoreapi.clients.client.Client;
import br.com.bookstoreapi.clients.client.ClientRepository;
import br.com.bookstoreapi.clients.exception.AccessNotAllowedException;
import br.com.bookstoreapi.clients.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static br.com.bookstoreapi.clients.client.service.ServiceJWT.getEmailFromToken;

@RequiredArgsConstructor
@Service
public class UpdateClientServiceImpl implements UpdateClientService {

    private final ClientRepository clientRepository;


    @Override
    public Client update(UUID id, Client client, String bearerToken) throws EntityNotFoundException, AccessNotAllowedException {
        Optional<Client> clientOptional = clientRepository.findByUuid(id);
        if(clientOptional.isPresent()){
            if(!getEmailFromToken(bearerToken).equals(client.getEmail())) {
                throw new AccessNotAllowedException();
            }
            client.setId(clientOptional.get().getId());
            client.setUuid(id);
            return clientRepository.save(client);
        }
        throw new EntityNotFoundException(id, Client.class.getSimpleName());
    }
}
