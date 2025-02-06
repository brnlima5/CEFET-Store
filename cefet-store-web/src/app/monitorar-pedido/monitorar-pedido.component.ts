import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../services/auth/auth.service';

@Component({
  selector: 'app-monitorar-pedido',
  standalone: false,
  
  templateUrl: './monitorar-pedido.component.html',
  styleUrl: './monitorar-pedido.component.scss'
})
export class MonitorarPedidoComponent {

  buscarPedidoForm!: FormGroup;
  pedido: any;

  constructor(private fb: FormBuilder,
    private authService: AuthService,
  ) {  }

  ngOnInit() {
    this.buscarPedidoForm = this.fb.group({
      trackingId: [null, [Validators.required]]
    })
  }

  submitForm() {
    this.authService.buscarPedidoByTrackingId(this.buscarPedidoForm.get('trackingId').value).subscribe(res =>{
      console.log(res);
      this.pedido = res;
    })
  }

}
