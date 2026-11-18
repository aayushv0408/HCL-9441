package com.hcld2.jwtdemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequest(@NotBlank @Size(max = 200) String title, @NotBlank String content) {
}
