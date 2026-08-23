package com.certificados.app.service;

import com.certificados.app.dto.CertificadoDTO;
import com.certificados.app.exception.BusinessException;
import com.certificados.app.exception.ResourceNotFoundException;
import com.certificados.app.model.Certificado;
import com.certificados.app.model.EstadoCertificado;
import com.certificados.app.model.Estudiante;
import com.certificados.app.repository.CertificadoRepository;
import com.certificados.app.repository.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CertificadoService {

    private final CertificadoRepository certificadoRepository;
    private final EstudianteRepository estudianteRepository;

    public List<CertificadoDTO> listarTodos() {
        return certificadoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<CertificadoDTO> listarPorEstudiante(Long estudianteId) {
        return certificadoRepository.findByEstudianteId(estudianteId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CertificadoDTO buscarPorId(Long id) {
        return toDTO(obtenerEntidad(id));
    }

    public CertificadoDTO solicitar(CertificadoDTO dto) {
        Estudiante estudiante = estudianteRepository.findById(dto.getEstudianteId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Estudiante no encontrado con id " + dto.getEstudianteId()));

        Certificado certificado = new Certificado();
        certificado.setTipo(dto.getTipo());
        certificado.setEstudiante(estudiante);
        certificado.setEstado(EstadoCertificado.PENDIENTE);
        certificado.setFechaSolicitud(LocalDate.now());
        certificado.setObservaciones(dto.getObservaciones());
        certificado.setCodigoVerificacion(generarCodigoVerificacion());

        return toDTO(certificadoRepository.save(certificado));
    }

    public CertificadoDTO emitir(Long id) {
        Certificado certificado = obtenerEntidad(id);
        if (certificado.getEstado() == EstadoCertificado.ANULADO) {
            throw new BusinessException("No se puede emitir un certificado anulado");
        }
        certificado.setEstado(EstadoCertificado.EMITIDO);
        certificado.setFechaEmision(LocalDate.now());
        return toDTO(certificadoRepository.save(certificado));
    }

    public CertificadoDTO anular(Long id) {
        Certificado certificado = obtenerEntidad(id);
        certificado.setEstado(EstadoCertificado.ANULADO);
        return toDTO(certificadoRepository.save(certificado));
    }

    private Certificado obtenerEntidad(Long id) {
        return certificadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Certificado no encontrado con id " + id));
    }

    private String generarCodigoVerificacion() {
        return "CERT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private CertificadoDTO toDTO(Certificado c) {
        CertificadoDTO dto = new CertificadoDTO();
        dto.setId(c.getId());
        dto.setCodigoVerificacion(c.getCodigoVerificacion());
        dto.setTipo(c.getTipo());
        dto.setEstado(c.getEstado());
        dto.setFechaSolicitud(c.getFechaSolicitud());
        dto.setFechaEmision(c.getFechaEmision());
        dto.setObservaciones(c.getObservaciones());
        dto.setEstudianteId(c.getEstudiante().getId());
        dto.setNombreEstudiante(c.getEstudiante().getNombres() + " " + c.getEstudiante().getApellidos());
        return dto;
    }
}
