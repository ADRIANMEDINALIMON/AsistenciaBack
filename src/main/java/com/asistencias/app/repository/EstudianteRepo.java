
package com.asistencias.app.repository;

import com.asistencias.app.models.Estudiante;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EstudianteRepo extends JpaRepository<Estudiante, Long>{
    
    Optional<Estudiante> findByCodigo(String codigo);
}
