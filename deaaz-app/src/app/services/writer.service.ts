import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Writer} from '../entities/writer';

@Injectable({
  providedIn: 'root'
})
export class WriterService {
  private baseUrl = 'http://localhost:8080/api/writers';

  constructor(private httpClient: HttpClient) { }

  getWriter( id: number ) {
    return this.httpClient.get(this.baseUrl + '/${id}', {withCredentials: true});
  }

  getListWriter() {
    return this.httpClient.get(this.baseUrl, {withCredentials: true});
  }

  getListWriterWithParams(value: string[], field: string[], operator: string[]) {
    let params = new HttpParams();
    params = params.append('value', value.join(', '));
    params = params.append('field', field.join(', '));
    params = params.append('operator', operator.join(', '));

    return this.httpClient.get(this.baseUrl,{params: params, withCredentials: true});
  }

  createWriter(writer: Writer) {
    return this.httpClient.put(this.baseUrl, writer, {withCredentials: true});
  }

  updateWriter(writer: Writer) {
    return this.httpClient.post(this.baseUrl, writer, {withCredentials: true});
  }

  deleteWriter( id: number){
    return this.httpClient.delete(this.baseUrl + '/${id}', {withCredentials: true});
  }
}
