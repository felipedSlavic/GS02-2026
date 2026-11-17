package br.fiap.felipe565687.dto;

import java.time.LocalDateTime;

public record LeituraDTO(
        Long id,
        LocalDateTime data,
        Integer laminaCm,
        String status,
        Long trechoId,
        String descricaoTrecho) {
}
