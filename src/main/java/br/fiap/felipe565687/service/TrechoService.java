package br.fiap.felipe565687.service;


import br.fiap.felipe565687.domain.Bairro;
import br.fiap.felipe565687.domain.Trecho;
import br.fiap.felipe565687.dto.TrechoDTO;
import br.fiap.felipe565687.mapper.TrechoMapper;
import br.fiap.felipe565687.repository.LeituraRepostiory;
import br.fiap.felipe565687.repository.TrechoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TrechoService {
    private final TrechoRepository repo;
    private final TrechoMapper mapper;
    private final BairroService bairroService;
    private final LeituraRepostiory leituraRepo;

    @Transactional
    public TrechoDTO salvar(TrechoDTO dto) {
        Bairro bairro = bairroService.buscarEntidade(dto.id());
        Trecho trecho = mapper.paraEntidade(dto, bairro);
        Trecho trechoSalvo = repo.save(trecho);
        return mapper.paraDTO(trechoSalvo);
    }

    @Transactional(readOnly = true)
    public List<TrechoDTO> listar() {
        return repo.findAllByOrderByLogradouroAsc()
                .stream()
                .map(mapper::paraDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public TrechoDTO buscarPorId(Long id) {
        Trecho trecho = repo.findById(id).orElseThrow();
        return mapper.paraDTO(trecho);
    }

    public Trecho buscarEntidade(Long id){
        return repo.findById(id).orElseThrow();
    }


    @Transactional
    public void excluir(Long id) {
        Trecho trecho = buscarEntidade(id);
        if (leituraRepo.existsByTrecho_Id(id)){
            leituraRepo.deleteByTrecho_Id(id);
        }
        repo.delete(trecho);
    }
}