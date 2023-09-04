package br.com.bookstoreapi.clients.client.v1;

import br.com.bookstoreapi.clients.client.ClientDTO;
import br.com.bookstoreapi.clients.client.ClientRecieveDTO;
import br.com.bookstoreapi.clients.client.service.*;
import br.com.bookstoreapi.clients.exception.AccessNotAllowedException;
import br.com.bookstoreapi.clients.exception.DeleteException;
import br.com.bookstoreapi.clients.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RequestMapping("/v1/clients")
@RequiredArgsConstructor
@RestController
public class ClientController {

    private final GetAllClientService getAllClientService;
    private final GetClientService getClientService;
    private final SaveClientService postClientService;
    private final UpdateClientService putClientService;
    private final DeleteClientService deleteClientService;

    private final TotalElementService totalElementService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> list(Pageable pageable){
        return ClientDTO.fromAll(getAllClientService.findAll(pageable));
    }

    @GetMapping("/elements/total")
    @ResponseStatus(HttpStatus.OK)
    public Integer totalElements(){
        return this.totalElementService.getTotalElement();
    }

    @GetMapping("/private/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO find(@PathVariable UUID clientId,  @RequestHeader("Authorization") String bearerToken) throws EntityNotFoundException, AccessNotAllowedException {
        return ClientDTO.from(getClientService.getByUuid(clientId, bearerToken));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO save(@RequestBody @Valid ClientRecieveDTO client){
        return ClientDTO.from(postClientService.save(ClientRecieveDTO.to(client)));
    }

    @PutMapping("/private/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO update(@PathVariable UUID clientId,
                            @RequestBody @Valid ClientRecieveDTO clientDTO,
                            @RequestHeader("Authorization") String bearerToken) throws EntityNotFoundException, AccessNotAllowedException {
        return ClientDTO.from(putClientService.update(clientId,ClientRecieveDTO.to(clientDTO), bearerToken));
    }

    @DeleteMapping("/private/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID clientId, @RequestHeader("Authorization") String bearerToken) throws EntityNotFoundException, DeleteException, AccessNotAllowedException {
        deleteClientService.delete(clientId, bearerToken);
    }
}
