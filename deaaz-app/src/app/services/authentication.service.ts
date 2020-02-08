import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../entities/user';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private baseUrl = 'http://localhost:8080/api/authenticate';
  public currentUserLogin: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(private httpClient: HttpClient) {
    this.currentUserLogin = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserLogin.asObservable();
  }

  loginUser( user: User ) {
    return this.httpClient.post(this.baseUrl, user, {withCredentials: true});
  }

  logout() {
    localStorage.removeItem('currentUser');
    this.currentUserLogin.next(null);
  }
}
