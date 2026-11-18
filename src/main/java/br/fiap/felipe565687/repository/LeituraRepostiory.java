package br.fiap.felipe565687.repository;

import br.fiap.felipe565687.domain.Leitura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeituraRepostiory extends JpaRepository<Leitura, Long> {
    List<Leitura> findAllByOrderByDataHoraDesc();
    Optional<Leitura> findFirstByTrechoIdOrderByDataHoraDesc(long trechoId);
    boolean existsByTrechoId(long trechoId);
    void deleteByTrechoId(long trechoId);
}
