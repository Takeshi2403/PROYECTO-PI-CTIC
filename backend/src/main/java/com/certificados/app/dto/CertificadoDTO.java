package com.certificados.app.dto;

import com.certificados.app.model.EstadoCertificado;
import com.certificados.app.model.TipoCertificado;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificadoDTO {
    private Long id;
    private String codigoVerificacion;

    @NotNull(message = "El tipo de certificado es obligatorio")
    private TipoCertificado tipo;

    private EstadoCertificado estado;
    private LocalDate fechaSolicitud;
    private LocalDate fechaEmision;
    private String observaciones;

    @NotNull(message = "El estudiante es obligatorio")
    private Long estudianteId;

    private String nombreEstudiante;
}
