package cl.hsys.auth.registration.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//DTO del registro (lo que manda Angular)

public record RegisterRequest (

    @NotBlank
    @Size(min = 3, max = 64)
    String username,

    @NotBlank
    @Email
    String mail,

    String phone,

    @NotBlank
    @Size(min = 8, max = 128)
    String password,

    @NotBlank
    @Size(min = 2, max = 160)
    String companyName,

    @NotBlank(message = "TaxId es obligatorio")
    @Size(max = 32)
    String taxId,

    String timezone

){}