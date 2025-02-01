import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvaliacaoProdutoPedidoComponent } from './avaliacao-produto-pedido.component';

describe('AvaliacaoProdutoPedidoComponent', () => {
  let component: AvaliacaoProdutoPedidoComponent;
  let fixture: ComponentFixture<AvaliacaoProdutoPedidoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AvaliacaoProdutoPedidoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AvaliacaoProdutoPedidoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
