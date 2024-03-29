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
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class GetClientServiceImplTest {

    private GetClientServiceImpl getClientService;
    @Mock
    private ClientRepository clientRepository;


    @BeforeEach
    void setUp(){
        getClientService = new GetClientServiceImpl(clientRepository);
     }

    @Test
    void testGetByIdWhenIdExist() throws Exception{
        when(clientRepository.findByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(Optional.of(ClientBuilder.clientJenipapo1()));

        Client client = getClientService.getByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"), null);
        assertThat(client.getId(), is(1L));
        assertThat(client.getUuid().toString(), is("12d51c0a-a843-46fc-8447-5fda559ec69b"));
        assertThat(client.getName(), is("Jenipapo"));
        assertThat(client.getAge(), is(19));
        assertThat(client.getEmail(), is("jenipapo@coldmail.com"));
        assertThat(client.getTelephone(), is("83996438691"));
        assertThat(client.getGender(), is("Male"));
    }

   @Test
    void testGetByIdWhenIdDontExist(){
       when(clientRepository.findByUuid(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
               .thenReturn(Optional.empty());
       assertThrows(EntityNotFoundException.class, ()-> getClientService.getByUuid(null, null));
   }
}