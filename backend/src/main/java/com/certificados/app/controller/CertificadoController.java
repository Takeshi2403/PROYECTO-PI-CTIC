package com.certificados.app.controller;

import com.certificados.app.dto.CertificadoDTO;
import com.certificados.app.service.CertificadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certificados")
@RequiredArgsConstructor
public class CertificadoController {

    private final CertificadoService certificadoService;

    @GetMapping
    public List<CertificadoDTO> listar() {
        return certificadoService.listarTodos();
    }

    @GetMapping("/estudiante/{estudianteId}")
    public List<CertificadoDTO> listarPorEstudiante(@PathVariable Long estudianteId) {
        return certificadoService.listarPorEstudiante(estudianteId);
    }

    @GetMapping("/{id}")
    public CertificadoDTO buscarPorId(@PathVariable Long id) {
        return certificadoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CertificadoDTO solicitar(@Valid @RequestBody CertificadoDTO dto) {
        return certificadoService.solicitar(dto);
    }

    @PatchMapping("/{id}/emitir")
    public CertificadoDTO emitir(@PathVariable Long id) {
        return certificadoService.emitir(id);
    }

    @PatchMapping("/{id}/anular")
    public CertificadoDTO anular(@PathVariable Long id) {
        return certificadoService.anular(id);
    }
}
