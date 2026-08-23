export type TipoCertificado = 'ESTUDIO_REGULAR' | 'NOTAS' | 'EGRESADO' | 'CONDUCTA' | 'PASANTIAS';
export type EstadoCertificado = 'PENDIENTE' | 'EMITIDO' | 'ANULADO';

export interface Certificado {
  id?: number;
  codigoVerificacion?: string;
  tipo: TipoCertificado;
  estado?: EstadoCertificado;
  fechaSolicitud?: string;
  fechaEmision?: string;
  observaciones?: string;
  estudianteId: number;
  nombreEstudiante?: string;
}
