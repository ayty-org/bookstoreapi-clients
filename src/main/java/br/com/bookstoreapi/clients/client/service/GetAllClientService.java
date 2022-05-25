package br.com.bookstoreapi.clients.client.service;


import br.com.bookstoreapi.clients.client.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@FunctionalInterface
public interface GetAllClientService {

    List<Client> findAll(Pageable pageable);
}
