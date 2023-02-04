
package com.asistencias.app.service;

import com.asistencias.app.models.Asistencia;
import com.asistencias.app.models.Aula;
import com.asistencias.app.models.Estudiante;
import com.asistencias.app.repository.AsistenciaRepo;
import com.asistencias.app.repository.AulaRepo;
import com.asistencias.app.repository.EstudianteRepo;
import java.time.LocalDate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class AulaServiceImpl implements AulaService{

    private final AsistenciaRepo asistenciaRepo;
    private final EstudianteRepo estudianteRepo;
    private final AulaRepo aulaRepo;

    @Override
    public Optional<Aula> buscarAulaPorId(Long id) {
        return aulaRepo.findById(id);
    }

    @Override
    public Optional<Asistencia> buscarAsistenciaPorCodigo(String codigo) {
        LocalDate fechaActual = LocalDate.now();
        return asistenciaRepo.findByEstudiante_CodigoAndFechaIngreso(codigo, fechaActual);
    }

    @Override
    public Optional<Estudiante> buscarEstudiantePorCodigo(String codigo) {
        return estudianteRepo.findByCodigo(codigo);
    }

    @Override
    public void registrarAsistencia(Asistencia asistencia) {
        LocalDate fechaActual = LocalDate.now();
        asistencia.setFechaIngreso(fechaActual);
        asistencia.setIngresoConfirmado(true);
        asistencia.setSalidaConfirmado(false);
        asistenciaRepo.save(asistencia);
    }

    @Override
    public void actualizarAsistencia(Asistencia asistencia) {
        asistencia.setSalidaConfirmado(true);
        asistenciaRepo.save(asistencia);
    }
}