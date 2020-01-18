import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {User} from '../entities/user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private baseUrl = 'http://localhost:8080/api/authenticate';

  constructor(private httpClient: HttpClient) { }

  loginUser( user: User ) {
    return this.httpClient.post(this.baseUrl, user);
  }
}
