package br.fiap.felipe565687.service;

import br.fiap.felipe565687.domain.Leitura;
import br.fiap.felipe565687.domain.Trecho;
import br.fiap.felipe565687.dto.PainelItemDTO;
import br.fiap.felipe565687.repository.LeituraRepostiory;
import br.fiap.felipe565687.repository.TrechoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PainelService {
    private final TrechoRepository trechoRepo;
    private final LeituraRepostiory leituraRepo;
    private final BairroService bairroService;

    @Transactional(readOnly = true)
    public List<PainelItemDTO> montarPainel(Long bairroId) {
        bairroService.buscarEntidade(bairroId);
        List<PainelItemDTO> listaPainel = trechoRepo.findByBairroIdOrderByLogradouroAsc(bairroId)
                .stream()
                .map(this::montarItem)
                .toList();
        return listaPainel;
    }

    private PainelItemDTO montarItem(Trecho trecho) {
        Optional<Leitura> ultimaLeitura = leituraRepo.findFirstByTrechoIdOrderByDataHoraDesc(trecho.getId());

        PainelItemDTO itenDTO = new PainelItemDTO(trecho.getLogradouro(), trecho.getTipoCalcada(), trecho.getAltitude(),
                ultimaLeitura.map(Leitura::getLaminaCm).orElse(null), ultimaLeitura.map(Leitura::getStatus).orElse(null));
        return itenDTO;
    }

    public long contadorStatus(List<PainelItemDTO> listaDeItens, String status){
        return listaDeItens.stream()
                .filter(item -> status.equals(item.ultimoStatus()))
                .count();
    }

}
