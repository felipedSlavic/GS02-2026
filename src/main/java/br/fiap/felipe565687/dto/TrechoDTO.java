package br.fiap.felipe565687.dto;

public record TrechoDTO(
        Long id,
        Double altitude,
        String logradouro,
        String tipoCalcada,
        Long bairroId,
        String nomeBairro) {
}
