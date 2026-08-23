package com.certificados.app.repository;

import com.certificados.app.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    Optional<Estudiante> findByCodigoEstudiantil(String codigoEstudiantil);
    boolean existsByCodigoEstudiantil(String codigoEstudiantil);
    boolean existsByEmail(String email);
}
