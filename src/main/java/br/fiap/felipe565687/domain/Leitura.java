package br.fiap.felipe565687.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "GS142_LEITURA")
@Getter
@Setter
public class Leitura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LEITURA")
    private Long id;

    @Column(name = "DATA_HORA", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "LAMINA_CM", nullable = false)
    private int laminaCm;

    @Column(name = "STATUS",nullable = false, length = 10)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TRECHO_ID", nullable = false)
    private Trecho trecho;
}
