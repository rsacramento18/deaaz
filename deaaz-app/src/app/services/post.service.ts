import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Post} from '../entities/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private baseUrl = 'http://localhost:8080/api/posts';

  constructor(private httpClient: HttpClient) { }

  getPost( id: number ) {
    return this.httpClient.get(this.baseUrl + '/${id}', {withCredentials: true});
  }

  getListPost() {
    return this.httpClient.get(this.baseUrl, {withCredentials: true});
  }

  createPost(post: Post) {
    return this.httpClient.put(this.baseUrl, post, {withCredentials: true});
  }

  updatePost(post: Post) {
    return this.httpClient.post(this.baseUrl, post, {withCredentials: true});
  }

  deletePost( id: number){
    return this.httpClient.delete(this.baseUrl + '/${id}', {withCredentials: true});
  }
}
