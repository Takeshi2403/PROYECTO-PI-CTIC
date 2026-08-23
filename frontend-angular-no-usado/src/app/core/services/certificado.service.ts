import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Certificado } from '../models/certificado.model';

@Injectable({ providedIn: 'root' })
export class CertificadoService {
  private baseUrl = `${environment.apiUrl}/certificados`;

  constructor(private http: HttpClient) {}

  listar(): Observable<Certificado[]> {
    return this.http.get<Certificado[]>(this.baseUrl);
  }

  listarPorEstudiante(estudianteId: number): Observable<Certificado[]> {
    return this.http.get<Certificado[]>(`${this.baseUrl}/estudiante/${estudianteId}`);
  }

  solicitar(certificado: Certificado): Observable<Certificado> {
    return this.http.post<Certificado>(this.baseUrl, certificado);
  }

  emitir(id: number): Observable<Certificado> {
    return this.http.patch<Certificado>(`${this.baseUrl}/${id}/emitir`, {});
  }

  anular(id: number): Observable<Certificado> {
    return this.http.patch<Certificado>(`${this.baseUrl}/${id}/anular`, {});
  }
}
