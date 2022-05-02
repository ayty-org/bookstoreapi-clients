package br.com.bookstoreapi.clients.client.v1;

import br.com.bookstoreapi.clients.BookstoreClientsApplicationTests;
import br.com.bookstoreapi.clients.builders.ClientBuilder;
import br.com.bookstoreapi.clients.client.Client;
import br.com.bookstoreapi.clients.client.ClientDTO;
import br.com.bookstoreapi.clients.client.ClientRecieveDTO;
import br.com.bookstoreapi.clients.client.ClientRepository;
import br.com.bookstoreapi.clients.client.service.*;
import br.com.bookstoreapi.clients.client.v1.ClientController;
import br.com.bookstoreapi.clients.purchase.PurchaseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
public class ClientControllerTest extends BookstoreClientsApplicationTests {

    private MockMvc mockMvc;

    private ClientController clientController;

    @Autowired
    private GetAllClientService getAllClientService;
    @Autowired
    private GetClientService getClientService;
    @Autowired
    private SaveClientService saveClientService;
    @Autowired
    private UpdateClientService updateClientService;

    @Autowired
    private ClientRepository clientRepository;
    @Mock
    private PurchaseRepository purchaseRepository;

    private final String url = "/v1/clients";
    ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    void setUp() {
        DeleteClientService deleteClientService = new DeleteClientServiceImpl(clientRepository, purchaseRepository);
        this.clientController = new ClientController(getAllClientService, getClientService, saveClientService
                , updateClientService, deleteClientService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    void saveTest() throws Exception {
        String json1 = mapper.writeValueAsString(ClientBuilder.clientJenipapoRecieve());

        this.mockMvc.perform(post(this.url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json1))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Jenipapo")))
                .andExpect(jsonPath("$.age", is(19)))
                .andExpect(jsonPath("$.email", is("jenipapo@coldmail.com")))
                .andExpect(jsonPath("$.telephone", is("83996438691")))
                .andExpect(jsonPath("$.gender", is("Male")));
    }

    @Test
    void saveWhenClientIsInvalidTest() throws Exception {
        String json1 = mapper.writeValueAsString(ClientBuilder.clientInvalid());

        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json1))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAllTest() throws Exception {
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid", is("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .andExpect(jsonPath("$[0].name", is("Jenipapo")))
                .andExpect(jsonPath("$[0].age", is(19)))
                .andExpect(jsonPath("$[0].email", is("jenipapo@coldmail.com")))
                .andExpect(jsonPath("$[0].telephone", is("83996438691")))
                .andExpect(jsonPath("$[0].gender", is("Male")))
                .andExpect(jsonPath("$[1].uuid", is("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .andExpect(jsonPath("$[1].name", is("Ana")))
                .andExpect(jsonPath("$[1].age", is(46)))
                .andExpect(jsonPath("[1].email", is("ana@coldmail.com")))
                .andExpect(jsonPath("$[1].telephone", is("83996438691")))
                .andExpect(jsonPath("$[1].gender", is("Female")));
    }

    @Test
    void getOneTest() throws Exception {
        mockMvc.perform(get(url + "/12d51c0a-a843-46fc-8447-5fda559ec69b"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .andExpect(jsonPath("$.name", is("Jenipapo")))
                .andExpect(jsonPath("$.age", is(19)))
                .andExpect(jsonPath("$.email", is("jenipapo@coldmail.com")))
                .andExpect(jsonPath("$.telephone", is("83996438691")))
                .andExpect(jsonPath("$.gender", is("Male")));
    }

    @Test
    void getWhenDontExistTest() {
        Assertions.assertThatThrownBy(() ->
                        mockMvc.perform(get(url + "/12d51c0a-a843-46fc-8447-5fda559ec699"))
                                .andExpect(status().isNotFound()))
                .hasMessageContaining("Client with id 12d51c0a-a843-46fc-8447-5fda559ec699 not found");
    }

    @Test
    void putWhenIdExistTest() throws Exception {
        ClientRecieveDTO c1 = ClientBuilder.clientJenipapoRecieve();
        c1.setName("Newmar");
        c1.setAge(44);
        c1.setEmail("newmar@gmail.com");

        String json1 = mapper.writeValueAsString(c1);
        mockMvc.perform(put(url + "/27eaa649-e8fa-4889-bd5a-ea6825b71e61")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.uuid", is("27eaa649-e8fa-4889-bd5a-ea6825b71e61")))
                .andExpect(jsonPath("$.name", is("Newmar")))
                .andExpect(jsonPath("$.age", is(44)))
                .andExpect(jsonPath("$.email", is("newmar@gmail.com")))
                .andExpect(jsonPath("$.telephone", is("83996438691")))
                .andExpect(jsonPath("$.gender", is("Male")));
    }

    @Test
    void putWhenIdDontExistTest() throws Exception {
        Client c1 = ClientBuilder.clientJenipapo1();
        String json1 = mapper.writeValueAsString(ClientDTO.from(c1));
        Assertions.assertThatThrownBy(() -> mockMvc.perform(put(
                                url + "/df670f4b-5d4d-4f70-ae78-f2ddc9fa1f19")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json1))
                        .andExpect(MockMvcResultMatchers.status().isNotFound()))
                .hasMessageContaining("Client with id df670f4b-5d4d-4f70-ae78-f2ddc9fa1f19 not found");
    }

    @Test
    void putWhenClientIsInvalid() throws Exception {
        String json1 = mapper.writeValueAsString(ClientBuilder.clientInvalid());

        mockMvc.perform(put(url + "/12d51c0a-a843-46fc-8447-5fda559ec69b")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json1))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteWhenIdExist() throws Exception {
        mockMvc.perform(delete(url + "/27eaa649-e8fa-4889-bd5a-ea6825b71e6b"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Assertions.assertThatThrownBy(() -> mockMvc.perform(get(url + "/27eaa649-e8fa-4889-bd5a-ea6825b71e6b"))
                .andExpect(MockMvcResultMatchers.status().isNotFound()));
    }

    @Test
    void deleteWhenIdDontExist() {
        Assertions.assertThatThrownBy(() -> mockMvc.perform(delete(url + "/27eaa649-e8fa-4889-bd5a-ea6825b71e10"))
                        .andExpect(MockMvcResultMatchers.status().isNotFound()))
                .hasMessageContaining("Client with id 27eaa649-e8fa-4889-bd5a-ea6825b71e10 not found");
    }

    @Test
    void deleteWhenExistPurchaseWithClient() {
        when(purchaseRepository.existsByClientUuid(any())).thenReturn(true);

        Assertions.assertThatThrownBy(() -> mockMvc.perform(delete(url + "/12d51c0a-a843-46fc-8447-5fda559ec69b"))
                        .andExpect(MockMvcResultMatchers.status().isConflict()))
                .hasMessageContaining("Client with id 12d51c0a-a843-46fc-8447-5fda559ec69b " +
                        "cannot be deleted because it is in one or more purchases");
    }
}