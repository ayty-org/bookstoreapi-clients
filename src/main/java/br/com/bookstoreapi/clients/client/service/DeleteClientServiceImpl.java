package br.com.bookstoreapi.clients.client.service;

import br.com.bookstoreapi.clients.client.Client;
import br.com.bookstoreapi.clients.client.ClientRepository;
import br.com.bookstoreapi.clients.exception.DeleteException;
import br.com.bookstoreapi.clients.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DeleteClientServiceImpl implements DeleteClientService {

    private final ClientRepository clientRepository;
    //private final PurchaseRepository purchaseRepository;


    @Override
    public void delete(UUID id) throws EntityNotFoundException, DeleteException {
        Optional<Client> clientOptional = clientRepository.findByUuid(id);
        if(clientOptional.isPresent()){
//            if (purchaseRepository.existsByClientUuid(id)) {
//                throw new DeleteException(id, Client.class.getSimpleName());
//            }
            this.clientRepository.delete(clientOptional.get());
        }else{
            throw new EntityNotFoundException(id, Client.class.getSimpleName());
        }

    }
}
