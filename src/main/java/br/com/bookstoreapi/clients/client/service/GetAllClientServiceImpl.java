package br.com.bookstoreapi.clients.client.service;

import br.com.bookstoreapi.clients.client.Client;
import br.com.bookstoreapi.clients.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllClientServiceImpl implements GetAllClientService {

    private final ClientRepository clientRepository;


    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
