import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-painel',
  standalone: false,
  
  templateUrl: './painel.component.html',
  styleUrl: './painel.component.scss'
})
export class PainelComponent {

  products: any[] = [];

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.getAllProducts(); 
  }

  getAllProducts() {
    this.products = [];
    this.adminService.getAllProdutos().subscribe(res => {
      res.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,' + element.byteImg;
        this.products.push(element);
      });
    })
  }

}
