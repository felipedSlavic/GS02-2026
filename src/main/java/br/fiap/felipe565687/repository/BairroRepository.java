package br.fiap.felipe565687.repository;

import br.fiap.felipe565687.domain.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BairroRepository  extends JpaRepository<Bairro, Long> {
    List<Bairro> findAllByOrderByNomeAsc();

    List<Bairro> id(long id);
}
