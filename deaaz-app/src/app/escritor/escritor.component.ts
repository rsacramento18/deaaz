import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../services/authentication.service';
import {User} from '../entities/user';
import {Writer} from "../entities/writer";
import {Post} from "../entities/post";
import {WriterService} from "../services/writer.service";
import {PostService} from "../services/post.service";

@Component({
  selector: 'app-escritor',
  templateUrl: './escritor.component.html',
  styleUrls: ['./escritor.component.scss']
})
export class EscritorComponent implements OnInit {
  private loginForm: FormGroup;
  private updateUserForm: FormGroup;
  public user: User;
  public writers: Writer[];
  public posts: Post[];
  private isLogged = false;
  private component = 1;
  public value: string[] = [];
  public field: string[] = [];
  public operator: string[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private authenticationService: AuthenticationService,
    private writerService: WriterService,
    private postService: PostService
  ) {

    this.authenticationService.currentUser.subscribe(x => {
      this.isLogged = x !== null;
      this.user = x;
    });
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });

    this.updateUserForm = this.formBuilder.group( {
      username: ['', [Validators.required]],
      email: ['', [Validators.required]]
    });

    if(this.user !== null) {
      this.updateUserForm.get('username').setValue(this.user.username);
      this.updateUserForm.get('email').setValue(this.user.email);
    }

  }

  onFormSubmit() {
    if (this.loginForm.valid) {
      this.user = new User();

      this.user.username = this.loginForm.controls.username.value;
      this.user.password = this.loginForm.controls.password.value;

      this.authenticationService.loginUser(this.user).subscribe((res: User) => {
        this.user = res;

        window.localStorage.setItem('currentUser', JSON.stringify(this.user));

        this.authenticationService.currentUserLogin.next(this.user);
        this.getWriters();

      });
    }
  }

  getWriters() {
    console.log(this.user);
    console.log(this.value);
    if ( this.user.id != null) {
      this.value.push(String(this.user.id));
      this.field.push('user');
      this.operator.push('EQUAL');
      this.writerService.getListWriterWithParams(this.value, this.field, this.operator).subscribe( (res: Writer[]) => {
        console.log(res);
        res = this.writers;
      });

    }
  }

  logout() {
    this.authenticationService.logout();
  }

  showComponent(num) {
    this.component = num;
  }

}
