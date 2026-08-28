package com.certificados.app.service;

import com.certificados.app.dto.CargaArchivoResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface CargarArchivoService {
    CargaArchivoResponseDTO cargarArchivo(MultipartFile archivo, String tipoDocumento);
}