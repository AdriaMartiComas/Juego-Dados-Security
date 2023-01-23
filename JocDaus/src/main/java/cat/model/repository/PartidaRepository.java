package cat.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cat.model.domain.Partida;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Integer> {

}
