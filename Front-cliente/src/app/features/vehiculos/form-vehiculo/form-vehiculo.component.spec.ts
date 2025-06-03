import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormVehiculoComponent } from './form-vehiculo.component';

describe('FormVehiculoComponent', () => {
  let component: FormVehiculoComponent;
  let fixture: ComponentFixture<FormVehiculoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormVehiculoComponent]
    });
    fixture = TestBed.createComponent(FormVehiculoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
