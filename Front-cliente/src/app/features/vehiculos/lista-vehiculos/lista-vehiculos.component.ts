import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { VehiculoResponse } from 'src/app/core/model/vehiculo-response.model';
import { VehiculoService } from 'src/app/core/services/vehiculo.service';

@Component({
  selector: 'app-lista-vehiculos',
  templateUrl: './lista-vehiculos.component.html',
  styleUrls: ['./lista-vehiculos.component.css']
})
export class ListaVehiculosComponent implements OnInit{
  vehiculos: VehiculoResponse[] = [];
  constructor(
    private vehiculoService: VehiculoService,
    private router: Router) {}

  ngOnInit(): void {
    this.vehiculoService.getVehiculosByUsuarioId().subscribe({
      next: (data) => this.vehiculos = data,
      error: (err) => console.error(err)
    });
  }
  verDetalle(vehiculo: VehiculoResponse) {
  // Aquí puedes abrir un modal, navegar con router, etc.
  console.log('Detalle del vehículo:', vehiculo);
  this.router.navigate(['/dashboard/vehiculos/detalle', vehiculo.id]);
  }
}
