export interface Vehiculo {
  marca: string;
  modelo: string;
  color: string;
  placa: string;
  anio: number;
  tipoVehiculo: string;
  estado: string;
  usuarioId: number;
}

// Para crear, se utiliza la licencia completa:
export type VehiculoCreateRequest = Vehiculo;

// Para actualizar, omites el usuarioId:
export type VehiculoUpdateRequest = Omit<Vehiculo, 'usuarioId'>;
