package br.fiap.felipe565687.repository;

import br.fiap.felipe565687.domain.Trecho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrechoRepository extends JpaRepository<Trecho , Long> {
    List<Trecho> findAllByOrderByLogradouroAsc();
    List<Trecho> findByBairro_IdOrderByLogradouroAsc(long bairroId);
    boolean existsByBairro_Id(long bairroId);
}
