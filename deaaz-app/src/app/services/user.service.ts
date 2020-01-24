import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {User} from '../entities/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = 'http://localhost:8080/api/users';

  constructor(private httpClient: HttpClient) { }

  getUser( id: number ) {
    return this.httpClient.get(this.baseUrl + '/${id}', {withCredentials: true});
  }

  getListUser() {
    return this.httpClient.get(this.baseUrl, {withCredentials: true});
  }

  createUser( user: User) {
    return this.httpClient.put(this.baseUrl, user);
  }

  updateUser( user: User) {
    return this.httpClient.post(this.baseUrl, user);
  }

  deleteUser( id: number){
    return this.httpClient.delete(this.baseUrl + '/${id}');
  }
}
