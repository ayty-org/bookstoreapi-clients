package br.com.bookstoreapi.clients.client.service;

import br.com.bookstoreapi.clients.builders.ClientBuilder;
import br.com.bookstoreapi.clients.client.Client;
import br.com.bookstoreapi.clients.client.ClientRepository;
import br.com.bookstoreapi.clients.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
class UpdateClientServiceImplTest {

    private UpdateClientServiceImpl updateClientService;
    @Mock
    private ClientRepository repository;


    @BeforeEach
    void setUp(){
        this.updateClientService = new UpdateClientServiceImpl(repository);
    }

    @Test
    void updateTest() throws Exception{
        when(repository.findByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(Optional.of(ClientBuilder.clientJenipapo1()));
        when(repository.save(any())).thenReturn(ClientBuilder.clientJenipapo1());

        Client client = updateClientService.update(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"),
                ClientBuilder.clientJenipapo1(), null);

        assertThat(client.getId(), is(1L));
        assertThat(client.getUuid().toString(), is("12d51c0a-a843-46fc-8447-5fda559ec69b"));
        assertThat(client.getName(), is("Jenipapo"));
        assertThat(client.getAge(), is(19));
        assertThat(client.getEmail(), is("jenipapo@coldmail.com"));
        assertThat(client.getTelephone(), is("83996438691"));
        assertThat(client.getGender(), is("Male"));

        verify(repository, times(1)).save(any());
    }

    @Test
    void updateWhenIdDontExist(){
        when(repository.findByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                ()-> updateClientService.update(null, ClientBuilder.clientJenipapo1(), null));
        verify(repository, never()).save(any());
    }
}