import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';
import { VehiculoService } from 'src/app/core/services/vehiculo.service';

@Component({
  selector: 'app-form-vehiculo',
  templateUrl: './form-vehiculo.component.html',
  styleUrls: ['./form-vehiculo.component.css']
})
export class FormVehiculoComponent implements OnInit,OnChanges {
  @Input() vehiculoId: number | null = null; // Optional: Input for edit mode
  vehiculoForm!: FormGroup;
  isEditMode: boolean = false;
  isLoading: boolean = false;
  errorMessage: string | null = null;
  maxYear: number = new Date().getFullYear();
  usuarioId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private vehiculoService: VehiculoService,
    private authService: AuthService,
    private route: ActivatedRoute) {}

  ngOnInit() {
    this.usuarioId = this.authService.getUsuarioAuthId(); // Obtener ID usuario desde token

    this.initializeForm();
    // Check for vehicle ID from route (alternative to @Input)
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.isEditMode = true;
        this.vehiculoId = +id;
        this.loadVehiculo(+id);
      }
    });
  }
  ngOnChanges(changes: SimpleChanges) {
    // Handle changes to vehiculoId input
    if (changes['vehiculoId'] && changes['vehiculoId'].currentValue) {
      this.isEditMode = true;
      this.loadVehiculo(changes['vehiculoId'].currentValue);
    }
  }

  initializeForm() {
    this.vehiculoForm = this.fb.group({
      marca: ['', [Validators.required, Validators.minLength(2)]],
      modelo: ['', [Validators.required, Validators.minLength(2)]],
      color: ['', Validators.required],
      placa: ['', [Validators.required, Validators.pattern(/^[A-Z0-9-]{6,8}$/)]], // Example regex for license plate
      anio: [null, [Validators.required, Validators.min(1900), Validators.max(new Date().getFullYear())]],
      tipoVehiculo: ['', Validators.required],
      estado: [{ value: 'Activo', disabled: true }, Validators.required],
      usuarioId: [{value: null, disabled: true}, [Validators.required, Validators.min(1)]]
    });
  }
  loadVehiculo(id: number) {
    this.isLoading = true;
    this.vehiculoService.getVehiculoById(id).subscribe({
      next: (vehiculo) => {
        this.vehiculoForm.patchValue(vehiculo); // Populate form with vehicle data
        // En modo edición, no permitimos editar el estado
        this.vehiculoForm.get('estado')?.disable();
        // El usuarioId también no debe cambiarse
        this.vehiculoForm.get('usuarioId')?.disable();
        this.isLoading = false;
      },
      error: (error) => {
        this.errorMessage = 'Error al cargar el vehículo';
        this.isLoading = false;
        console.error(error);
      }
    });
  }
  submitForm() {
    if (this.vehiculoForm.invalid) {
      this.vehiculoForm.markAllAsTouched(); // Mark all fields as touched to show validation errors
      return;
    }

    this.isLoading = true;
    this.errorMessage = null;

      // Para enviar los valores aunque estén deshabilitados, usamos getRawValue()
    const formData = this.vehiculoForm.getRawValue();
    // Forzamos usuarioId a que siempre sea el del token (por seguridad)
    formData.usuarioId = this.usuarioId;
    // Forzamos estado a 'Activo' en creación (si quieres forzar siempre el estado al crear)
    if (!this.isEditMode) {
      formData.estado = 'Activo';
    }

    const request = this.isEditMode && this.vehiculoId
      ? this.vehiculoService.updateVehiculo(this.vehiculoId, formData)
      : this.vehiculoService.createVehiculo(formData);

    request.subscribe({
      next: () => {
        const message = this.isEditMode ? 'Vehículo actualizado correctamente' : 'Vehículo registrado correctamente';
        this.showSuccess(message);
        this.resetForm();
        this.isLoading = false;
      },
      error: (error) => {
        this.errorMessage = this.isEditMode ? 'Error al actualizar el vehículo' : 'Error al registrar el vehículo';
        this.isLoading = false;
        console.error(error);
      }
    });
  }
  resetForm() {
    this.vehiculoForm.reset({
      estado: 'Activo', // Preserve default value
      usuarioId: this.usuarioId
    });
    this.vehiculoForm.get('estado')?.enable();
    this.vehiculoForm.get('usuarioId')?.enable();
    this.isEditMode = false;
    this.vehiculoId = null;
  }

  showSuccess(message: string) {
    // Replace alert with a better notification (e.g., Angular Material Snackbar)
    alert(message); // Temporary, consider using a toast service
  }
}
