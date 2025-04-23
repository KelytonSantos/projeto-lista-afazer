package com.list.to_do.DTO;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record TaskSharedDTO(@NotEmpty String acessLevel, @NotEmpty @Email String email,
        @org.hibernate.validator.constraints.UUID UUID id) {

}
