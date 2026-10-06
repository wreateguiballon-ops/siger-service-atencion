package sat.siger.service.atencion.repository;

import sat.siger.service.atencion.model.Requerimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequerimientoRepository extends JpaRepository<Requerimiento, Integer>, AtencionRepositoryCustom {
}
