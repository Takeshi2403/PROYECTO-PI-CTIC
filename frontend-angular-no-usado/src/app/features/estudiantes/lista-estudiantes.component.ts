import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Estudiante } from '../../core/models/estudiante.model';
import { EstudianteService } from '../../core/services/estudiante.service';

@Component({
  selector: 'app-lista-estudiantes',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './lista-estudiantes.component.html'
})
export class ListaEstudiantesComponent implements OnInit {
  estudiantes: Estudiante[] = [];
  cargando = true;

  constructor(private estudianteService: EstudianteService) {}

  ngOnInit(): void {
    this.cargar();
  }

  cargar(): void {
    this.cargando = true;
    this.estudianteService.listar().subscribe({
      next: (data) => {
        this.estudiantes = data;
        this.cargando = false;
      },
      error: () => (this.cargando = false)
    });
  }

  eliminar(id: number | undefined): void {
    if (!id) return;
    if (!confirm('¿Eliminar este estudiante?')) return;
    this.estudianteService.eliminar(id).subscribe(() => this.cargar());
  }
}
