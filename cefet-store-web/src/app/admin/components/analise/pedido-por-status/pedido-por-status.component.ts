import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-pedido-por-status',
  standalone: false,
  
  templateUrl: './pedido-por-status.component.html',
  styleUrl: './pedido-por-status.component.scss'
})
export class PedidoPorStatusComponent {


  @Input() data:any;
}
