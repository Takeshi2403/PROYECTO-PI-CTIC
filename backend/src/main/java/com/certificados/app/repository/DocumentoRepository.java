package com.certificados.app.model.repository;

import com.certificados.app.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long>{
   Optional<Documento> findByRutaAlmacenamiento(String rutaAlmacenamiento);
}