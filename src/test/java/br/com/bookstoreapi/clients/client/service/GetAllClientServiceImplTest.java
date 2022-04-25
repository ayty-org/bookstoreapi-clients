package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.builders.ClientBuilder;
import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class GetAllClientServiceImplTest {

    private GetAllClientServiceImpl getAllClientService;
    @Mock
    private ClientRepository clientRepository;


    @BeforeEach
    void setUp(){
        this.getAllClientService = new GetAllClientServiceImpl(clientRepository);
    }

    @Test
    void findAllTest(){
        when(clientRepository.findAll()).thenReturn(ClientBuilder.clientList());
        List<Client> clients = getAllClientService.findAll();

        assertThat(3, is(clients.size()));
        verify(clientRepository, times(1)).findAll();

        assertThat(clients.get(0).getId(), is(1L));
        assertThat(clients.get(0).getUuid().toString(), is("12d51c0a-a843-46fc-8447-5fda559ec69b"));
        assertThat(clients.get(0).getName(), is("Jenipapo"));
        assertThat(clients.get(0).getAge(), is(19));
        assertThat(clients.get(0).getEmail(), is("jenipapo@coldmail.com"));
        assertThat(clients.get(0).getTelephone(), is("83996438691"));
        assertThat(clients.get(0).getGender(), is("Male"));

        assertThat(clients.get(1).getId(), is(2L));
        assertThat(clients.get(1).getUuid().toString(), is("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14"));
        assertThat(clients.get(1).getName(), is("Ana"));
        assertThat(clients.get(1).getAge(), is(46));
        assertThat(clients.get(1).getEmail(), is("ana@coldmail.com"));
        assertThat(clients.get(1).getTelephone(), is("83996438691"));
        assertThat(clients.get(1).getGender(), is("Female"));

        assertThat(clients.get(2).getId(), is(3L));
        assertThat(clients.get(2).getUuid().toString(), is("27eaa649-e8fa-4889-bd5a-ea6825b71e61"));
        assertThat(clients.get(2).getName(), is("Patricia"));
        assertThat(clients.get(2).getAge(), is(25));
        assertThat(clients.get(2).getEmail(), is("patricia@coldmail.com"));
        assertThat(clients.get(2).getTelephone(), is("83996438691"));
        assertThat(clients.get(2).getGender(), is("Trans"));
    }
}