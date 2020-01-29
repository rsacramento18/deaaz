import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../services/authentication.service';
import {User} from '../entities/user';
import {Router} from "@angular/router";

@Component({
  selector: 'app-escritor',
  templateUrl: './escritor.component.html',
  styleUrls: ['./escritor.component.scss']
})
export class EscritorComponent implements OnInit {
  private loginForm: FormGroup;
  private updateUserForm: FormGroup;
  public user: User;
  private isLogged = false;
  private component = 1;

  constructor(
    private formBuilder: FormBuilder,
    private authenticationService: AuthenticationService,
    private router: Router
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

        console.log(res);
        console.log(this.user);
      });
    }
  }

  showComponent(num) {
    this.component = num;
  }

}
