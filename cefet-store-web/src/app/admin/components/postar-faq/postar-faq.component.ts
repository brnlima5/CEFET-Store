import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-postar-faq',
  standalone: false,
  
  templateUrl: './postar-faq.component.html',
  styleUrl: './postar-faq.component.scss'
})
export class PostarFaqComponent {

  produtoId: number;
  FAQForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private adminService: AdminService,
    private activatedRoute: ActivatedRoute
  ) {}

  

  ngOnInit() {
    this.produtoId = this.activatedRoute.snapshot.params["produtoId"];
    console.log('Produto ID:', this.produtoId);
    this.FAQForm = this.fb.group({
      question: [null, [Validators.required]],
      answer: [null, [Validators.required]],
    })
  }
  
  postFAQ() {
    this.adminService.postFAQ(this.produtoId, this.FAQForm.value).subscribe(res =>{
      if(res.id != null) {
        this.snackBar.open('FAQ adicionado com sucesso!', 'Fechar', {
          duration: 5000
        });
        this.router.navigateByUrl('/admin/painel');
      } else {
        this.snackBar.open("Deu ruim", 'Fechar', {
          duration: 5000,
          panelClass: 'error-snackbar'
        });
      }
    })
  }

}
