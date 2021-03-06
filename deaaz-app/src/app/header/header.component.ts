import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../services/authentication.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  private isNavbarCollapsed=true;
  private isLogged = false;

  constructor(private authenticationService: AuthenticationService) {

    this.authenticationService.currentUser.subscribe(x => {
      this.isLogged = x !== null;
    });
  }

  ngOnInit() {
  }

  logOff() {
    this.authenticationService.logout();
  }
}
