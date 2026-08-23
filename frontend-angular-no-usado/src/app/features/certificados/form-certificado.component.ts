import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Estudiante } from '../../core/models/estudiante.model';
import { EstudianteService } from '../../core/services/estudiante.service';
import { CertificadoService } from '../../core/services/certificado.service';

@Component({
  selector: 'app-form-certificado',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './form-certificado.component.html'
})
export class FormCertificadoComponent implements OnInit {
  estudiantes: Estudiante[] = [];
  tipos = ['ESTUDIO_REGULAR', 'NOTAS', 'EGRESADO', 'CONDUCTA', 'PASANTIAS'];

  form = this.fb.group({
    estudianteId: [null, Validators.required],
    tipo: ['', Validators.required],
    observaciones: ['']
  });

  guardando = false;

  constructor(
    private fb: FormBuilder,
    private estudianteService: EstudianteService,
    private certificadoService: CertificadoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.estudianteService.listar().subscribe((data) => (this.estudiantes = data));
  }

  guardar(): void {
    if (this.form.invalid) return;
    this.guardando = true;
    this.certificadoService.solicitar(this.form.getRawValue() as any).subscribe({
      next: () => this.router.navigate(['/certificados']),
      error: () => (this.guardando = false)
    });
  }
}
