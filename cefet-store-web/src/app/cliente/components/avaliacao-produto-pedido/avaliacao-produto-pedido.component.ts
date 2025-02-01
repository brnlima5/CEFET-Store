import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ClienteService } from '../../services/cliente.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { UsuarioStorageService } from '../../../services/storage/usuario-storage.service';

@Component({
  selector: 'app-avaliacao-produto-pedido',
  standalone: false,
  
  templateUrl: './avaliacao-produto-pedido.component.html',
  styleUrl: './avaliacao-produto-pedido.component.scss'
})
export class AvaliacaoProdutoPedidoComponent {

  produtoId: number;
  avaliacaoForm!: FormGroup;
  selectedFile: File | null;
  imagePreview: string | ArrayBuffer | null;

  constructor( private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private clienteService: ClienteService,
    private router: Router,
    private activatedRoute: ActivatedRoute,

  ) {
  }

  ngOnInit() { 
    this.produtoId = this.activatedRoute.snapshot.params["produtoId"];
    this.avaliacaoForm = this.fb.group({
      rating: [null, [Validators.required]],
      description: [null, [Validators.required]],
    })
  }

  onFileSelected(event:any) {
    this.selectedFile = event.target.files[0];
    this.previewImage();
  }

  previewImage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    }
    reader.readAsDataURL(this.selectedFile);
  }

  submitForm() {
    const formData: FormData = new FormData();
    formData.append('img', this.selectedFile);
    formData.append('productId', this.produtoId.toString());
    formData.append('userId', UsuarioStorageService.getUserId().toString());
    formData.append('rating', this.avaliacaoForm.get('rating').value);
    formData.append('description', this.avaliacaoForm.get('description').value);

    this.clienteService.getAvaliacao(formData).subscribe(res =>{
      if(res.id != null) {
        this.snackBar.open('Avaliação enviada com sucesso!', 'Fechar', {
          duration: 5000
        });
        this.router.navigateByUrl('/cliente/meus-pedidos');
      } else {
        this.snackBar.open("Deu ruim", 'Fechar', {
          duration: 5000
        });
      }
    })
  }

}
