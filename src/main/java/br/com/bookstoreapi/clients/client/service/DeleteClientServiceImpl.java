package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import com.bookstoreapi.bookstoreapi.exception.DeleteException;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DeleteClientServiceImpl implements DeleteClientService {

    private final ClientRepository clientRepository;
    private final PurchaseRepository purchaseRepository;


    @Override
    public void delete(UUID id) throws EntityNotFoundException, DeleteException {
        Optional<Client> clientOptional = clientRepository.findByUuid(id);
        if(clientOptional.isPresent()){
            if (purchaseRepository.existsByClientUuid(id)) {
                throw new DeleteException(id, Client.class.getSimpleName());
            }
            this.clientRepository.delete(clientOptional.get());
        }else{
            throw new EntityNotFoundException(id, Client.class.getSimpleName());
        }

    }
}
