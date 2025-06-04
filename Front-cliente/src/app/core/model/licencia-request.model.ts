export interface Licencia {
  tipoLicencia: string;
  fechaVencimiento: string; // Puedes usar Date si prefieres trabajar con fechas
  estado: boolean;
  usuarioId: number;
}

// Para crear, se utiliza la licencia completa:
export type LicenciaCreateRequest = Licencia;

// Para actualizar, omites el usuarioId:
export type LicenciaUpdateRequest = Omit<Licencia, 'usuarioId'>;
