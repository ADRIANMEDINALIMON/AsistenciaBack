
package com.asistencias.app.repository;

import com.asistencias.app.models.Asistencia;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AsistenciaRepo extends JpaRepository<Asistencia, Long>{
    
    Optional<Asistencia> findByEstudiante_CodigoAndFechaIngreso(String codigo, LocalDate fechaIngreso);
}
