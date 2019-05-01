import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.scss']
})
export class BlogComponent implements OnInit {
  private letters: String[] = [];

  constructor() { }

  ngOnInit() {
    this.letters.push("A");
    this.letters.push("B");
    this.letters.push("C");
    this.letters.push("D");
    this.letters.push("E");
    this.letters.push("F");
    this.letters.push("G");
    this.letters.push("H");
    this.letters.push("I");
    this.letters.push("J");
    this.letters.push("K");
    this.letters.push("L");
    this.letters.push("M");
    this.letters.push("N");
    this.letters.push("O");
    this.letters.push("P");
    this.letters.push("Q");
    this.letters.push("R");
    this.letters.push("S");
    this.letters.push("T");
    this.letters.push("U");
    this.letters.push("V");
    this.letters.push("W");
    this.letters.push("X");
    this.letters.push("Y");
    this.letters.push("Z");
    console.log(this.letters);
  }

}
