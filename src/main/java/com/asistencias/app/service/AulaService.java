
package com.asistencias.app.service;

import com.asistencias.app.models.Asistencia;
import com.asistencias.app.models.Aula;
import com.asistencias.app.models.Estudiante;
import java.util.Optional;


public interface AulaService {
    
    Optional<Aula> buscarAulaPorId(Long id);
    Optional<Asistencia> buscarAsistenciaPorCodigo(String codigo);
    Optional<Estudiante> buscarEstudiantePorCodigo(String codigo);
    void registrarAsistencia(Asistencia asistencia);
    void actualizarAsistencia(Asistencia asistencia);

}
