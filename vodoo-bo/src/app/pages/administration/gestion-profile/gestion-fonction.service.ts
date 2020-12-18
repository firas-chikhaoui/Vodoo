import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GestionFonctionService  {

  
  private baseUrl = 'http://localhost:8080/v1/fonctions';

  constructor(private http: HttpClient) { }

  

  getFonctionsList(): Observable<any> {
      debugger
    return this.http.get(`${this.baseUrl}`);
  }

}