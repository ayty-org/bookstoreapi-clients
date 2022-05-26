package br.com.bookstoreapi.clients.client.service;

import br.com.bookstoreapi.clients.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TotalElementService {

    private final ClientRepository clientRepository;


    public Integer getTotalElement(){
        return this.clientRepository.findAll().toArray().length;
    }

}
