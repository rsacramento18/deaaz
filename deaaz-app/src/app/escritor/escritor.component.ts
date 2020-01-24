import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../services/authentication.service';
import {User} from '../entities/user';

@Component({
  selector: 'app-escritor',
  templateUrl: './escritor.component.html',
  styleUrls: ['./escritor.component.scss']
})
export class EscritorComponent implements OnInit {
  private loginForm: FormGroup;
  private user: User;

  constructor(private formBuilder: FormBuilder,
              private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });
  }

  onFormSubmit() {
    if (this.loginForm.valid) {
      this.user = new User();

      this.user.username = this.loginForm.controls.username.value;
      this.user.password = this.loginForm.controls.password.value;

      console.log(this.user);

      console.log(this.loginForm);
      this.authenticationService.loginUser(this.user).subscribe((response: any) => {
        console.log('response ', response.token);
        window.localStorage.setItem('currentUser', response.token);
      });

    }
  }

}
