package br.com.bookstoreapi.clients.client.service;

import br.com.bookstoreapi.clients.client.Client;
import br.com.bookstoreapi.clients.client.ClientRepository;
import br.com.bookstoreapi.clients.exception.AccessNotAllowedException;
import br.com.bookstoreapi.clients.exception.DeleteException;
import br.com.bookstoreapi.clients.exception.EntityNotFoundException;
import br.com.bookstoreapi.clients.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static br.com.bookstoreapi.clients.client.service.ServiceJWT.getEmailFromToken;

@RequiredArgsConstructor
@Service
public class DeleteClientServiceImpl implements DeleteClientService {

    private final ClientRepository clientRepository;
    private final PurchaseRepository purchaseRepository;


    @Override
    public void delete(UUID id, String bearerToken) throws EntityNotFoundException, DeleteException, AccessNotAllowedException {
        Optional<Client> clientOptional = clientRepository.findByUuid(id);
        if (clientOptional.isPresent()) {
            if(!getEmailFromToken(bearerToken).equals(clientOptional.get().getEmail())) {
                throw new AccessNotAllowedException();
            }
            if (purchaseRepository.existsByClientUuid(id)) {
                throw new DeleteException(id, Client.class.getSimpleName());
            }
            this.clientRepository.delete(clientOptional.get());
        } else {
            throw new EntityNotFoundException(id, Client.class.getSimpleName());
        }

    }
}
