package br.com.bookstoreapi.clients.client.service;

import br.com.bookstoreapi.clients.client.Client;
import br.com.bookstoreapi.clients.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SaveClientServiceImpl implements SaveClientService {

    private final ClientRepository clientRepository;


    @Override
    public Client save(Client client) {
        client.setUuid(UUID.randomUUID());
        return clientRepository.save(client);
    }
}
