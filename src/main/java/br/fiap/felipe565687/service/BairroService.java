package br.fiap.felipe565687.service;

import br.fiap.felipe565687.domain.Bairro;
import br.fiap.felipe565687.dto.BairroDTO;
import br.fiap.felipe565687.mapper.BairroMapper;
import br.fiap.felipe565687.repository.BairroRepository;
import br.fiap.felipe565687.repository.TrechoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@AllArgsConstructor
public class BairroService {
    private final BairroRepository repo;
    private final BairroMapper mapper;
    private final TrechoRepository trecchoRepo;


    @Transactional
    public BairroDTO salvar(BairroDTO dto) {
        Bairro bairro = mapper.paraEntidade(dto);
        Bairro bairroSalvo = repo.save(bairro);
        return mapper.paraDTO(bairroSalvo);
    }

    @Transactional(readOnly = true)
    public List<BairroDTO> listar(){
        return repo.findAllByOrderByNomeAsc()
                .stream()
                .map(mapper::paraDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public BairroDTO buscarPorId(Long id){
        Bairro bairro = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("O Bairro não  foi encontrado  ou não existe"));
        return mapper.paraDTO(bairro);
    }

    public Bairro buscarEntidade(Long id){
        return repo.findById(id).orElseThrow(()-> new IllegalArgumentException("O Bairro não  foi encontrado"));
    }

    @Transactional
    public void excluir(Long id) {
        if (trecchoRepo.existsByBairroId(id)){
          throw new IllegalArgumentException("O Bairro não pode ser excluído porque possui trechos");
         } else {
            repo.deleteById(id);
        }

    }

}
