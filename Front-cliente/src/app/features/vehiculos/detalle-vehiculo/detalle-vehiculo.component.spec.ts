import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalleVehiculoComponent } from './detalle-vehiculo.component';

describe('DetalleVehiculoComponent', () => {
  let component: DetalleVehiculoComponent;
  let fixture: ComponentFixture<DetalleVehiculoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DetalleVehiculoComponent]
    });
    fixture = TestBed.createComponent(DetalleVehiculoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
