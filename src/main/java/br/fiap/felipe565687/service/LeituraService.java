package br.fiap.felipe565687.service;

import br.fiap.felipe565687.domain.Bairro;
import br.fiap.felipe565687.domain.Leitura;
import br.fiap.felipe565687.domain.Trecho;
import br.fiap.felipe565687.dto.LeituraDTO;
import br.fiap.felipe565687.dto.TrechoDTO;
import br.fiap.felipe565687.mapper.LeituraMapper;
import br.fiap.felipe565687.mapper.TrechoMapper;
import br.fiap.felipe565687.repository.LeituraRepostiory;
import br.fiap.felipe565687.repository.TrechoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class LeituraService {
    private final LeituraRepostiory repo;
    private final LeituraMapper mapper;
    private final TrechoService trechoService;

    @Transactional
    public LeituraDTO salvar(LeituraDTO dto) {
        Trecho trecho = trechoService.buscarEntidade(dto.trechoId());
        String status = calcularStatus(dto.laminaCm());
        Leitura leitura = mapper.paraEntidade(dto,trecho,status);
        Leitura leituraSalva = repo.save(leitura);
        return mapper.paraDTO(leituraSalva);
    }
    @Transactional(readOnly = true)
    public List<LeituraDTO> listar(){
        return repo.findAllByOrderByDataHoraDesc()
                .stream()
                .map(mapper::paraDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public LeituraDTO buscarPorId(Long id){
        Leitura leitura = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("A Leitura não foi encontrada"));
        return mapper.paraDTO(leitura);
    }

    @Transactional
    public void excluir(Long id){
        repo.deleteById(id);
    }

    public String calcularStatus(int laminaCm){
        int limiarDeAlerta = 14;
        if (laminaCm >= limiarDeAlerta){
            return "ALERTA";
        } else if (laminaCm < limiarDeAlerta/2){
            return "NORMAL";
        }else {
            return "ATENCAO";
        }
    }

}