package br.com.bookstoreapi.clients.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Optional<Client> findByUuid(UUID uuid);
    Boolean existsByEmailAndPassword(String email, String password);
}
