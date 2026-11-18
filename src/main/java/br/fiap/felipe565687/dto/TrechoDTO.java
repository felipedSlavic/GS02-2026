package br.fiap.felipe565687.dto;

import jakarta.validation.constraints.*;

public record TrechoDTO(
        Long id,
        @NotNull @Min(0)  @Max(2000) Integer altitude,
        @NotBlank @Size(max = 120) String logradouro,
        @NotBlank String tipoCalcada,
        @NotNull Long bairroId,
        String nomeBairro) {
}
