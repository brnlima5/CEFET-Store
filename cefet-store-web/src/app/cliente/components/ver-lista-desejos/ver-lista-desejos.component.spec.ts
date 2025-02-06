import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerListaDesejosComponent } from './ver-lista-desejos.component';

describe('VerListaDesejosComponent', () => {
  let component: VerListaDesejosComponent;
  let fixture: ComponentFixture<VerListaDesejosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VerListaDesejosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VerListaDesejosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
