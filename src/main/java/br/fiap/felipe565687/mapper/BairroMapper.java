package br.fiap.felipe565687.mapper;

import br.fiap.felipe565687.domain.Bairro;
import br.fiap.felipe565687.dto.BairroDTO;
import org.springframework.stereotype.Component;

@Component
public class BairroMapper {
    public Bairro paraEntidade(BairroDTO dto){
        Bairro bairro = new Bairro();
        bairro.setId(dto.id());
        bairro.setNome(dto.nome());
        bairro.setZona(dto.zona());
        bairro.setPopulacao(dto.populacao());
        return bairro;
    }

    public BairroDTO paraDTO(Bairro bairro){
        return new BairroDTO(
                bairro.getId(),
                bairro.getNome(),
                bairro.getZona(),
                bairro.getPopulacao());
    }
}
