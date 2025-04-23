package com.list.to_do.DTO;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(@NotBlank String login, @NotBlank String password) {

}
