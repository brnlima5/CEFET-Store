import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { UsuarioStorageService } from '../storage/usuario-storage.service';

const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor( 
    private http: HttpClient,
    private usuarioStorageService: UsuarioStorageService,
  ) { }

  registro(signupRequest:any): Observable<any> {
    return this.http.post(BASIC_URL + "registrar", signupRequest);
  }

  login(username: string, password: string): any {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const body = {username, password};

    return this.http.post(BASIC_URL + 'authenticate', body, { headers, observe: 'response' }).pipe(
      map((res) => {
        const token = res.headers.get('authorization').substring(7);
        const user = res.body;

        if(token && user) {
          this.usuarioStorageService.saveToken(token);
          this.usuarioStorageService.saveUser(user);
          return true;
        }
        return false
      })
      
    )
  }

  buscarPedidoByTrackingId(trackingId: number): Observable<any> {
    return this.http.get(BASIC_URL + `pedido/${trackingId}`);
  }
}
