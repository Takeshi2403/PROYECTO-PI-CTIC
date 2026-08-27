package com.certificados.app.model;

import java.time.LocalDateTime;

public class Documento{
    private String id;
    private String nombreOriginal;
    private String rutaAlmacenamiento;
    private String tipoDocumento;
    private long tamanoBytes;
    private LocalDateTime fechaCarga;

    public Documento() {}

    public Documento(String id, String nombreOriginal, String rutaAlmacenamiento, String tipoDocumento, long tamanoBytes){
       this.id = id;
       this.nombreOriginal = nombreOriginal;
       this.rutaAlmacenamiento = rutaAlmacenamiento;
       this.tipoDocumento =tipoDocumento;
       this.tamanoBytes = tamanoBytes;
       this.fechaCarga = LocalDateTime.now(); 
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombreOriginal() { return nombreOriginal; }
    public void setNombreOriginal(String nombreOriginal) { this.nombreOriginal = nombreOriginal; }

    public String getRutaAlmacenamiento() { return rutaAlmacenamiento; }
    public void setRutaAlmacenamiento(String rutaAlmacenamiento) { this.rutaAlmacenamiento = rutaAlmacenamiento; }

    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    public long getTamanoBytes() { return tamanoBytes; }
    public void setTamanoBytes(long tamanoBytes) { this.tamanoBytes = tamanoBytes; }

    public LocalDateTime getFechaCarga() { return fechaCarga; }
    public void setFechaCarga(LocalDateTime fechaCarga) { this.fechaCarga = fechaCarga; }

}