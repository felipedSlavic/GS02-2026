package br.fiap.felipe565687.mapper;


import br.fiap.felipe565687.domain.Leitura;
import br.fiap.felipe565687.domain.Trecho;
import br.fiap.felipe565687.dto.LeituraDTO;
import org.springframework.stereotype.Component;

@Component
public class LeituraMapper {
    public Leitura paraEntidade(LeituraDTO dto, Trecho trecho,String status){
        Leitura leitura = new Leitura();
        leitura.setId(dto.id());
        leitura.setDataHora(dto.dataHora());
        leitura.setLaminaCm(dto.laminaCm());
        leitura.setStatus(status);
        leitura.setTrecho(trecho);
        return leitura;
    }

    public LeituraDTO paraDTO(Leitura leitura){
        return new LeituraDTO(
                leitura.getId(),
                leitura.getDataHora(),
                leitura.getLaminaCm(),
                leitura.getStatus(),
                leitura.getTrecho().getId(),
                leitura.getTrecho().getLogradouro());
    }

}
