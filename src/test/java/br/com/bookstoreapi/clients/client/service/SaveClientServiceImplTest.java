package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.builders.ClientBuilder;
import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class SaveClientServiceImplTest {

    private SaveClientServiceImpl saveClientServiceImpl;
    @Mock
    private ClientRepository repository;


    @BeforeEach
    void setUp(){
       this.saveClientServiceImpl = new SaveClientServiceImpl(repository);
    }

    @Test
    void saveTest(){
        when(repository.save(any())).thenReturn(ClientBuilder.clientJenipapo1());
        Client client = saveClientServiceImpl.save(ClientBuilder.clientJenipapo1());
        assertThat(client.getId(), is(1L));
        assertThat(client.getUuid().toString(), is("12d51c0a-a843-46fc-8447-5fda559ec69b"));
        assertThat(client.getName(), is("Jenipapo"));
        assertThat(client.getAge(), is(19));
        assertThat(client.getEmail(), is("jenipapo@coldmail.com"));
        assertThat(client.getTelephone(), is("83996438691"));
        assertThat(client.getGender(), is("Male"));

        verify(repository, times(1)).save(any());
    }
}