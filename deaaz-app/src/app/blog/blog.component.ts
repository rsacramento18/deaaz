import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user.service";
import {User} from "../entities/user";

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.scss']
})
export class BlogComponent implements OnInit {
  private letters: String[] = [];
  private tags: String[] = [];
  private user: User;

  constructor(private userServices: UserService) { }

  ngOnInit() {
    this.letters.push("A");
    this.letters.push("B");
    this.letters.push("C");
    this.letters.push("D");
    this.letters.push("E");
    this.letters.push("F");
    this.letters.push("G");
    console.log(this.letters);

    this.tags.push("Criativa");
    this.tags.push("Poesia");
    this.tags.push("Romance");
    this.tags.push("Crítica");
    this.tags.push("Crónica");
    this.tags.push("Novela");
    this.tags.push("Fábula");
    this.tags.push("Parábula");
    this.tags.push("Lenda");
    this.tags.push("Anedota");
  }


  getUsers() {
    this.userServices.getListUser().subscribe((users: User[]) => {
      console.log('response ', users);
    });
  }
}
