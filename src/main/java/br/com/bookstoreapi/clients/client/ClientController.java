package com.bookstoreapi.bookstoreapi.client;

import com.bookstoreapi.bookstoreapi.client.service.*;
import com.bookstoreapi.bookstoreapi.exception.DeleteException;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RequestMapping("/clients")
@RequiredArgsConstructor
@RestController
public class ClientController {

    private final GetAllClientService getAllClientService;
    private final GetClientService getClientService;
    private final SaveClientService postClientService;
    private final UpdateClientService putClientService;
    private final DeleteClientService deleteClientService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> list(){
        return ClientDTO.fromAll(getAllClientService.findAll());
    }

    @GetMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO find(@PathVariable UUID clientId) throws EntityNotFoundException {
        return ClientDTO.from(getClientService.getByUuid(clientId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO save(@RequestBody @Valid ClientRecieveDTO client){
        return ClientDTO.from(postClientService.save(ClientRecieveDTO.to(client)));
    }

    @PutMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO update(@PathVariable UUID clientId,
                            @RequestBody @Valid ClientRecieveDTO clientDTO) throws EntityNotFoundException {
        return ClientDTO.from(putClientService.update(clientId,ClientRecieveDTO.to(clientDTO)));
    }

    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID clientId) throws EntityNotFoundException, DeleteException {
        deleteClientService.delete(clientId);
    }
}
