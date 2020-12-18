import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GestionProfileService  {

  private baseUrl = 'http://localhost:8080/v1/profile';
  private baseUrl2 = 'http://localhost:8080/v1/profiles';
  private baseUrl3 = 'http://localhost:8080/v1/fonctions';

  constructor(private http: HttpClient) { }

  getProfile(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`); 
  }

  createProfile(Profil: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, Profil);
  }

  updateProfile(id: string, value: any): Observable<Object> {
    debugger
    return this.http.put(`${this.baseUrl}`, value);
  }

  deleteProfile(id: number): Observable<any> {
    debugger
    return this.http.delete(`${this.baseUrl}/${id}`, 
    { responseType: 'text' });
  }

  getProfilesList(): Observable<any> {
      debugger
    return this.http.get(`${this.baseUrl2}`);
  }

  getFonctionsList(): Observable<any> {
    debugger
  return this.http.get(`${this.baseUrl3}`);
}

}