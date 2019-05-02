import { Component, OnInit, Input } from '@angular/core';
import { Post } from '../entities/post';

@Component({
  selector: 'post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {

  // @Input()
  private post = new Post();

  private editorContent: string='My Document\'s Title';


  constructor() { }

  ngOnInit() {
    this.post.title="Destino";
    this.post.sub_title="Subtitulo";
    this.post.created_by="B";
    this.post.created_date="26/08/2018";
    this.post.content='<p style="margin-left: 140px;">Aterrorizante ser&aacute; o meu destino.&nbsp;</p><p style="margin-left: 140px;">Numa vida sem cria&ccedil;&atilde;o. Quando a inspira&ccedil;&atilde;o parar, estou certo que a morte me agarrar&aacute;.&nbsp;</p><p style="margin-left: 140px;">Mesmo que o meu corpo continue saud&aacute;vel. A minha alma apodrecer&aacute;. </p><p><br></p>';
    this.post.updated_date="27/08/2018";
    console.log(this.post);
  }

  onBtClick() {
    console.log(this.editorContent);
  }

}
