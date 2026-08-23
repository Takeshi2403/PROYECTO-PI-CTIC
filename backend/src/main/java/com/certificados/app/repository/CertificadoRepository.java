package com.certificados.app.repository;

import com.certificados.app.model.Certificado;
import com.certificados.app.model.EstadoCertificado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CertificadoRepository extends JpaRepository<Certificado, Long> {
    List<Certificado> findByEstudianteId(Long estudianteId);
    List<Certificado> findByEstado(EstadoCertificado estado);
    Optional<Certificado> findByCodigoVerificacion(String codigoVerificacion);
}
