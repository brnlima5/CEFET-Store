import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-cupons',
  standalone: false,
  
  templateUrl: './cupons.component.html',
  styleUrl: './cupons.component.scss'
})
export class CuponsComponent {

  cupons: any;

  constructor(private adminService: AdminService) {}

  ngOnInit(){
    this.getCupons();
  }

  getCupons() {
    this.adminService.getCupons().subscribe(res =>{
      this.cupons = res;
    })
  }

}
