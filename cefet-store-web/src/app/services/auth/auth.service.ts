import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASIC_URL = "https://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor( private https: HttpClient, ) { }

  registro(registroRequest:any): Observable<any> {
    return this.https.post(BASIC_URL + "registrar", registroRequest);
  }
}
