package br.fiap.felipe565687.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record LeituraDTO(
        Long id,
        @NotNull @PastOrPresent @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dataHora,
        @NotNull @Min(0) @Max(300) Integer laminaCm,
        String status,
        @NotNull Long trechoId,
        String descricaoTrecho) {
}
