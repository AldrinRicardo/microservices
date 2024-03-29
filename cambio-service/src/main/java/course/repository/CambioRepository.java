package course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import course.model.Cambio;

public interface CambioRepository extends JpaRepository<Cambio, Long> {

	Cambio findByFromAndTo(String from, String to);
	
}
