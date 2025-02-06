import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-analise',
  standalone: false,
  
  templateUrl: './analise.component.html',
  styleUrl: './analise.component.scss'
})
export class AnaliseComponent {

  data:any;

  constructor(
    private adminService: AdminService,
  ) {}

  ngOnInit(){
    this.adminService.getAnalise().subscribe(res =>{
      console.log(res);
      this.data = res;
    })   
  }

}
