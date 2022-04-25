package com.bookstoreapi.bookstoreapi.client;

import lombok.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDTO {

    private UUID uuid;
    private String name;
    private Integer age;
    private String telephone;
    private String email;
    private String gender;


    public static ClientDTO from(Client client) {
        return ClientDTO.builder()
                .uuid(client.getUuid())
                .name(client.getName())
                .age(client.getAge())
                .telephone(client.getTelephone())
                .email(client.getEmail())
                .gender(client.getGender())
                .build();
    }

    public static Client to(ClientDTO clientDTO) {
        return Client.builder()
                .uuid(clientDTO.getUuid())
                .name(clientDTO.getName())
                .age(clientDTO.getAge())
                .telephone(clientDTO.getTelephone())
                .email(clientDTO.getEmail())
                .gender(clientDTO.getGender())
                .build();
    }

    public static List<ClientDTO> fromAll(List<Client> clients) {
        return clients.stream()
                .map(ClientDTO::from)
                .collect(Collectors.toList());
    }
}
