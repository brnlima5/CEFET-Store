import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PedidoPorStatusComponent } from './pedido-por-status.component';

describe('PedidoPorStatusComponent', () => {
  let component: PedidoPorStatusComponent;
  let fixture: ComponentFixture<PedidoPorStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PedidoPorStatusComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PedidoPorStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
