package br.fiap.felipe565687.repository;

import br.fiap.felipe565687.domain.Trecho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrechoRepository extends JpaRepository<Trecho , Long> {
    List<Trecho> findAllByOrderByLogradouroAsc();
    List<Trecho> findByBairroIdOrderByLogradouroAsc(long bairroId);
    boolean existsByBairroId(long bairroId);
}
