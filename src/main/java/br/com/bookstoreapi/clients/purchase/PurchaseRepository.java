package br.com.bookstoreapi.clients.purchase;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "purchases", url = "http://localhost:8080/purchases")
public interface PurchaseRepository {

    @GetMapping("/existByClient/{uuid}")
    boolean existsByClientUuid(@PathVariable UUID uuid);

}
