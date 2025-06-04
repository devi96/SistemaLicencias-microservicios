export interface VehiculoResponse {
  id: number;
  marca: string;
  modelo: string;
  color: string;
  placa: string;
  anio: number;
  tipoVehiculo: string;
  estado: string;
  fechaRegistro: Date; // o Date si lo parseas
  usuarioId: number;
}
