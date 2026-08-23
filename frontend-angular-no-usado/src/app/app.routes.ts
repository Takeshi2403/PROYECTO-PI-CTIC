import { Routes } from '@angular/router';
import { ListaEstudiantesComponent } from './features/estudiantes/lista-estudiantes.component';
import { FormEstudianteComponent } from './features/estudiantes/form-estudiante.component';
import { ListaCertificadosComponent } from './features/certificados/lista-certificados.component';
import { FormCertificadoComponent } from './features/certificados/form-certificado.component';

export const routes: Routes = [
  { path: '', redirectTo: 'estudiantes', pathMatch: 'full' },
  { path: 'estudiantes', component: ListaEstudiantesComponent },
  { path: 'estudiantes/nuevo', component: FormEstudianteComponent },
  { path: 'certificados', component: ListaCertificadosComponent },
  { path: 'certificados/nuevo', component: FormCertificadoComponent },
  { path: '**', redirectTo: 'estudiantes' }
];
