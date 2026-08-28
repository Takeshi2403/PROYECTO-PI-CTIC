package com.certificados.app.service;

import org.springframework.web.multipart.MultipartFile;

public interface AlmacenamientoService {
    String guardar(MultipartFile archivo);
}