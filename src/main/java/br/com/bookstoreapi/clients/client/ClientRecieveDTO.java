package br.com.bookstoreapi.clients.client;

import lombok.*;

import javax.validation.constraints.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientRecieveDTO {

    @Size(min = 3, max = 60, message = "name must be between 3 and 60 characters")
    @NotBlank(message = "name cannot be null or void")
    private String name;

    @Max(value = 120, message = "age cannot be higher than 120")
    @Min(value = 0, message = "age cannot be lower than 0")
    @NotNull(message = "age cannot be null")
    private Integer age;

    @Size(min = 11, max = 11, message = "telephone must be 11 digits")
    @NotBlank(message = "telephone cannot be null or void")
    private String telephone;

    @Email(message = "email invalid")
    @NotBlank(message = "email cannot be null or void")
    private String email;

    @Size(min = 4, max = 15, message = "gender must be between 4 and 15 characters")
    @NotBlank(message = "gender cannot be null or void")
    private String gender;

    public static Client to(ClientRecieveDTO client){
        return Client.builder()
                .name(client.getName())
                .age(client.getAge())
                .telephone(client.getTelephone())
                .email(client.getEmail())
                .gender(client.getGender())
                .build();
    }
}
