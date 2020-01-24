import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from '../entities/user';
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private baseUrl = 'http://localhost:8080/api/authenticate';

  constructor(private httpClient: HttpClient) {
  }

  loginUser( user: User ) {
    return this.httpClient.post(this.baseUrl, user, {withCredentials: true});
  }
}
