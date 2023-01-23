package cat.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.model.domain.Usuari;

public interface UsuariRepository extends JpaRepository<Usuari, Integer> {
	Optional<Usuari> findByNomUsuari(String nomUsuari);
	boolean existsByNomUsuari(String nomUsuari);
}
