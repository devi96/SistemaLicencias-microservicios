import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormLicenciaComponent } from './form-licencia.component';

describe('FormLicenciaComponent', () => {
  let component: FormLicenciaComponent;
  let fixture: ComponentFixture<FormLicenciaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormLicenciaComponent]
    });
    fixture = TestBed.createComponent(FormLicenciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
