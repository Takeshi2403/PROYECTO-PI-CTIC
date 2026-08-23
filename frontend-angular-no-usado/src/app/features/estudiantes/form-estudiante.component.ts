import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EstudianteService } from '../../core/services/estudiante.service';

@Component({
  selector: 'app-form-estudiante',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './form-estudiante.component.html'
})
export class FormEstudianteComponent {
  form = this.fb.group({
    codigoEstudiantil: ['', Validators.required],
    nombres: ['', Validators.required],
    apellidos: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    programaAcademico: ['']
  });

  guardando = false;

  constructor(
    private fb: FormBuilder,
    private estudianteService: EstudianteService,
    private router: Router
  ) {}

  guardar(): void {
    if (this.form.invalid) return;
    this.guardando = true;
    this.estudianteService.crear(this.form.getRawValue() as any).subscribe({
      next: () => this.router.navigate(['/estudiantes']),
      error: () => (this.guardando = false)
    });
  }
}
