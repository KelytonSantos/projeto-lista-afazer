package com.list.to_do.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(@NotBlank String name, @NotNull @Email String email, @NotNull String password) {

}
