package br.fiap.felipe565687.service;

import br.fiap.felipe565687.domain.Bairro;
import br.fiap.felipe565687.dto.BairroDTO;
import br.fiap.felipe565687.mapper.BairroMapper;
import br.fiap.felipe565687.repository.BairroRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BairroService {
    private final BairroRepository repo;
    private final BairroMapper mapper;


    @Transactional
    public BairroDTO salvar(BairroDTO dto) {
        Bairro bairro = mapper.paraEntidade(dto);
        Bairro bairroSalvo = repo.save(bairro);
        return mapper.paraDTO(bairroSalvo);
    }

    public List<BairroDTO> listar(){
        return repo.findAllByOrderByNomeAsc()
                .stream()
                .map(mapper::paraDTO)
                .toList();
    }

    public BairroDTO buscarPorId(Long id){
        Bairro bairro = repo.findById(id).orElseThrow();
        return mapper.paraDTO(bairro);
    }

    public Bairro buscarEntidade(Long id){
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    public void excluir(Long id){
        repo.deleteById(id);
    }

}
