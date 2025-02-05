import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ClienteService } from '../../services/cliente.service';
import { ActivatedRoute } from '@angular/router';
import { UsuarioStorageService } from '../../../services/storage/usuario-storage.service';

@Component({
  selector: 'app-detalhe-produto',
  standalone: false,
  
  templateUrl: './detalhe-produto.component.html',
  styleUrl: './detalhe-produto.component.scss'
})
export class DetalheProdutoComponent {

  produtoId: number;

  produto: any;
  FAQS: any[] = [];
  avaliacoes: any[] = [];

  constructor(private snackBar: MatSnackBar,
    private clienteService: ClienteService,
    private activatedRoute: ActivatedRoute
  ) {
  }

  ngOnInit() {
    this.produtoId = this.activatedRoute.snapshot.params["produtoId"];
    this.getDetalheProdutoById();
  }

  getDetalheProdutoById() {
    this.clienteService.getDetalheProdutoById(this.produtoId).subscribe(res =>{
      this.produto = res.produtoDto;
      this.produto.processedImg = 'data:image/png;base64,' + res.produtoDto.byteImg;

      this.FAQS = res.faqDtoList;

      res.avaliacaoDtoList.forEach(element => {
        element.processedImg = 'data:image/png;base64,' + element.returnedImg;
        this.avaliacoes.push(element);
      });
    })
  }

  addListaDeDesejos() {
    const listaDesejosDto = {
      productId: this.produtoId,
      userId: UsuarioStorageService.getUserId()
    }

    this.clienteService.addProdutoListaDesejos(listaDesejosDto).subscribe(res =>{
      if(res.id = null) {
        this.snackBar.open('Produto adicionado à Lista de Desejos com sucesso!', 'Fechar', {
          duration: 5000
        })
      } else {
        this.snackBar.open('Este produto já está na Lista de Desejos.', 'Fechar', {
          duration: 5000
        });
      }
    })

  }

}
