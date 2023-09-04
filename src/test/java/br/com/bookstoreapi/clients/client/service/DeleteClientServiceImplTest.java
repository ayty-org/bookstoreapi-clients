package br.com.bookstoreapi.clients.client.service;

import br.com.bookstoreapi.clients.builders.ClientBuilder;
import br.com.bookstoreapi.clients.client.ClientRepository;
import br.com.bookstoreapi.clients.exception.DeleteException;
import br.com.bookstoreapi.clients.exception.EntityNotFoundException;
import br.com.bookstoreapi.clients.purchase.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class DeleteClientServiceImplTest {

    private DeleteClientServiceImpl deleteClientService;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private PurchaseRepository purchaseRepository;



    @BeforeEach
    void setUp() {
        this.deleteClientService = new DeleteClientServiceImpl(clientRepository,purchaseRepository);
    }

    @Test
    void deleteWhenIdExistTest() throws Exception{
        when(clientRepository.findByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"))
        ).thenReturn(Optional.of(ClientBuilder.clientJenipapo1()));

        deleteClientService.delete(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"), null);
        verify(clientRepository, times(1)).delete(any());
    }

    @Test
    void deleteWhenIdDontExistTest(){
        when(clientRepository.findByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                ()-> deleteClientService.delete(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"), null));

        verify(clientRepository, never()).delete(any());
    }

    @Test
    void deleteWhenExistPurchaseWithClient(){
        when(clientRepository.findByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"))
        ).thenReturn(Optional.of(ClientBuilder.clientJenipapo1()));
        when(purchaseRepository.existsByClientUuid(any())).thenReturn(true);
        assertThrows(DeleteException.class,
                ()-> deleteClientService.delete(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"), null));
        verify(clientRepository, never()).delete(any());
    }
}