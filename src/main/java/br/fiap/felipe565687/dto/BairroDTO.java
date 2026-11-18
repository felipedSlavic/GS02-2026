package br.fiap.felipe565687.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BairroDTO(
        Long Id,
        @NotBlank @Size(max = 80) String nome,
        @NotBlank String zona,
        @NotNull @Min(0) Integer populacao) {
}
