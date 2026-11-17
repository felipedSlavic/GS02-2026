package br.fiap.felipe565687.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "GS142_BAIRRO")
@Getter
@Setter
@NoArgsConstructor
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BAIRRO")
    private  long id;

    @Column(name = "NOME", nullable = false, length = 80)
    private String nome;

    @Column(name = "ZONA", nullable = false, length = 10)
    private String zona;

    @Column(name = "POPULACAO",nullable = false)
    private double populacao;

}
