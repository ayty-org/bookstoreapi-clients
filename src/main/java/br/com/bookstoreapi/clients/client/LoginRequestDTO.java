package br.com.bookstoreapi.clients.client;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequestDTO {

    private String email;
    private String password;
}
