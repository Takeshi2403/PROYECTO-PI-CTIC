import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Certificado } from '../../core/models/certificado.model';
import { CertificadoService } from '../../core/services/certificado.service';

@Component({
  selector: 'app-lista-certificados',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './lista-certificados.component.html'
})
export class ListaCertificadosComponent implements OnInit {
  certificados: Certificado[] = [];
  cargando = true;

  constructor(private certificadoService: CertificadoService) {}

  ngOnInit(): void {
    this.cargar();
  }

  cargar(): void {
    this.cargando = true;
    this.certificadoService.listar().subscribe({
      next: (data) => {
        this.certificados = data;
        this.cargando = false;
      },
      error: () => (this.cargando = false)
    });
  }

  emitir(id: number | undefined): void {
    if (!id) return;
    this.certificadoService.emitir(id).subscribe(() => this.cargar());
  }

  anular(id: number | undefined): void {
    if (!id) return;
    if (!confirm('¿Anular este certificado?')) return;
    this.certificadoService.anular(id).subscribe(() => this.cargar());
  }
}
