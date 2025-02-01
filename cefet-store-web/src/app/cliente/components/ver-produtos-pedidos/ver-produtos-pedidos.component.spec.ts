import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerProdutosPedidosComponent } from './ver-produtos-pedidos.component';

describe('VerProdutosPedidosComponent', () => {
  let component: VerProdutosPedidosComponent;
  let fixture: ComponentFixture<VerProdutosPedidosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VerProdutosPedidosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VerProdutosPedidosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
