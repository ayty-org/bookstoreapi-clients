package br.com.bookstoreapi.clients.client;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginResponseDTO {

    private String token;
}
