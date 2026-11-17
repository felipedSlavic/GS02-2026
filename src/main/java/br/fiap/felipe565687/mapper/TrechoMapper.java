package br.fiap.felipe565687.mapper;


import br.fiap.felipe565687.domain.Bairro;
import br.fiap.felipe565687.domain.Trecho;
import br.fiap.felipe565687.dto.TrechoDTO;
import org.springframework.stereotype.Component;

@Component
public class TrechoMapper {
        public Trecho paraEntidade(TrechoDTO dto, Bairro bairro){
            Trecho trecho = new Trecho();
            trecho.setId(dto.id());
            trecho.setAltitude(dto.altitude());
            trecho.setLogradouro(dto.logradouro());
            trecho.setTipoCalcada(dto.tipoCalcada());
            trecho.setBairro(bairro);
            return trecho;
        }

        public TrechoDTO paraDTO(Trecho trecho){
             TrechoDTO dto = new TrechoDTO(
                     trecho.getId(),
                     trecho.getAltitude(),
                     trecho.getLogradouro(),
                     trecho.getTipoCalcada(),
                     trecho.getBairro().getId(),
                     trecho.getBairro().getNome());
             return dto;
        }

}
