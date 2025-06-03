import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaLicenciaComponent } from './lista-licencia.component';

describe('ListaLicenciaComponent', () => {
  let component: ListaLicenciaComponent;
  let fixture: ComponentFixture<ListaLicenciaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListaLicenciaComponent]
    });
    fixture = TestBed.createComponent(ListaLicenciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
