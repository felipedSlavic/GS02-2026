package br.fiap.felipe565687.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "GS142_TRECHO")
@Getter
@Setter
public class Trecho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TRECHO")
    private long id;

    @Column(name = "ALTITUDE", nullable = false)
    private double altitude;

    @Column(name = "LOGRADOURO" , nullable = false, length = 120)
    private String logradouro;

    @Column(name = "TIPO_CALCADA" , nullable = false, length = 20)
    private String tipoCalcada;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BAIRRO_ID" , nullable = false)
    private Bairro bairro;
}
