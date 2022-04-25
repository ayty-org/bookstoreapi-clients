package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UpdateClientServiceImpl implements UpdateClientService {

    private final ClientRepository clientRepository;


    @Override
    public Client update(UUID id, Client client) throws EntityNotFoundException {
        Optional<Client> clientOptional = clientRepository.findByUuid(id);
        if(clientOptional.isPresent()){
            client.setId(clientOptional.get().getId());
            client.setUuid(id);
            return clientRepository.save(client);
        }
        throw new EntityNotFoundException(id, Client.class.getSimpleName());
    }
}
