export interface RegisterRequest {
  nombres: string;
  apellidos: string;
  password: string;
  email: string;
  telefono: string;
  direccion: string;
  rolId: number[];  // arreglo de IDs de roles
}
