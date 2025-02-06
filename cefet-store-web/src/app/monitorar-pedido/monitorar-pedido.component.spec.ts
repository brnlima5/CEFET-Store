import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MonitorarPedidoComponent } from './monitorar-pedido.component';

describe('MonitorarPedidoComponent', () => {
  let component: MonitorarPedidoComponent;
  let fixture: ComponentFixture<MonitorarPedidoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MonitorarPedidoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MonitorarPedidoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
