package br.fiap.felipe565687.mapper;


import br.fiap.felipe565687.domain.Leitura;
import br.fiap.felipe565687.domain.Trecho;
import br.fiap.felipe565687.dto.LeituraDTO;
import org.springframework.stereotype.Component;

@Component
public class LeituraMapper {
    public Leitura leitura(LeituraDTO dto, Trecho trecho){
        Leitura leitura = new Leitura();
        leitura.setId(dto.id());
        leitura.setData(dto.data());
        leitura.setLaminaCm(dto.laminaCm());
        leitura.setStatus(dto.status());
        leitura.setTrecho(trecho);
        return leitura;
    }

    public LeituraDTO paraDTO(Leitura leitura){
        return new LeituraDTO(
                leitura.getId(),
                leitura.getData(),
                leitura.getLaminaCm(),
                leitura.getStatus(),
                leitura.getTrecho().getId(),
                leitura.getTrecho().getLogradouro());
    }

}
