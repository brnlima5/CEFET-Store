import { Component } from '@angular/core';
import { ClienteService } from '../../services/cliente.service';

@Component({
  selector: 'app-ver-lista-desejos',
  standalone: false,
  
  templateUrl: './ver-lista-desejos.component.html',
  styleUrl: './ver-lista-desejos.component.scss'
})
export class VerListaDesejosComponent {

  produtos: any[] = [];

  constructor(
    private clienteService: ClienteService
  ) {}

  ngOnInit() {
    this.getListaDesejosByUserId();
  }

  getListaDesejosByUserId() {
    this.clienteService.getListaDesejosByUserId().subscribe(res=>{
      res.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImg;
        this.produtos.push(element);
      });
    })
  }

}
