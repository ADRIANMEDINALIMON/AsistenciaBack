package com.asistencias.app.controller;

import com.asistencias.app.models.Asistencia;
import com.asistencias.app.models.Aula;
import com.asistencias.app.models.Estudiante;
import com.asistencias.app.service.AulaService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/aulas")
public class AulaController {

    private final AulaService aulaService;

    @GetMapping("/buscar-aula/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Aula> aulaEncontrada = aulaService.buscarAulaPorId(id);

        if (!aulaEncontrada.isPresent()) {
            return new ResponseEntity<>("No se encontro el aula: " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(aulaEncontrada.get(), HttpStatus.OK);
    }

    @PostMapping("/registrar-asistencia")
    public ResponseEntity<?> registrarAsistencia(@RequestBody Asistencia asistencia) {
        Optional<Estudiante> estudianteEncontrado = aulaService.
                buscarEstudiantePorCodigo(asistencia.getEstudiante().getCodigo());

    if(!estudianteEncontrado.isPresent ()){
        return new ResponseEntity<>("No se Encontro estudiante con codigo;"
                + asistencia.getEstudiante().getCodigo(), HttpStatus.NOT_FOUND);
    }
    Optional<Asistencia> asistenciaEncontrada = aulaService.buscarAsistenciaPorCodigo(asistencia.getEstudiante().getCodigo());
    
    if(asistenciaEncontrada.isPresent()){
        return new ResponseEntity<>("No se puede registrar 2 veces la asistencia.", HttpStatus.BAD_REQUEST);
}
    asistencia.setEstudiante(estudianteEncontrado.get());
    aulaService.registrarAsistencia(asistencia);

    return new ResponseEntity<>(HttpStatus.CREATED);
}
    @PutMapping("/registar-salida")
    public ResponseEntity<?> actualizarAsistencia(@RequestBody Asistencia asistencia){
        Optional<Estudiante> estudianteEncontrado = aulaService.
                buscarEstudiantePorCodigo(asistencia.getEstudiante().getCodigo());

    if(!estudianteEncontrado.isPresent ()){
        return new ResponseEntity<>("No se Encontro estudiante con codigo;"
                + asistencia.getEstudiante().getCodigo(), HttpStatus.NOT_FOUND);
    }
    Optional<Asistencia> asistenciaEncontrada = aulaService.buscarAsistenciaPorCodigo(asistencia.getEstudiante().getCodigo());
    
    if(!asistenciaEncontrada.isPresent()){
        return new ResponseEntity<>("no hay asistencia registrada", HttpStatus.BAD_REQUEST);
    }
    
    aulaService.actualizarAsistencia(asistenciaEncontrada.get());
    
    return new ResponseEntity<>(HttpStatus.CREATED);
    
    }
    @GetMapping("/buscar-asistencia/{codigoEstudiante}")
    public ResponseEntity<?> buscarAsistenciaPorCodigo(@PathVariable String codigoEstudiante){
        Optional<Asistencia> asistenciaEncontrada = aulaService.buscarAsistenciaPorCodigo(codigoEstudiante);
        
        if(!asistenciaEncontrada.isPresent()){
            return new ResponseEntity<>("no se encontro asistencia con ese codigo: " + codigoEstudiante,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(asistenciaEncontrada.get(), HttpStatus.OK);
    }

}