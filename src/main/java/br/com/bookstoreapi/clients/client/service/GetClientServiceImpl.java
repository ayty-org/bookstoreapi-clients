package br.com.bookstoreapi.clients.client.service;

import br.com.bookstoreapi.clients.client.Client;
import br.com.bookstoreapi.clients.client.ClientRepository;
import br.com.bookstoreapi.clients.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetClientServiceImpl implements GetClientService{

    private final ClientRepository clientRepository;

    @Override
    public Client getByUuid(UUID uuid) throws EntityNotFoundException {
        return clientRepository.findByUuid(uuid)
                .orElseThrow(()-> new EntityNotFoundException(uuid, Client.class.getSimpleName()));
    }

    @Override
    public Boolean existByEmailAndPasswrd(String email, String password) throws EntityNotFoundException {
        return clientRepository.existsByEmailAndPassword(email, password);
    }
}
