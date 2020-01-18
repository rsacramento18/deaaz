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

  private editorContent = 'My Document\'s Title';


  constructor() { }

  ngOnInit() {
    this.post.title = 'Destino';
    this.post.subtitle = 'Subtitulo';
    this.post.createdBy = 'B';
    this.post.creationDate = '26/08/2018';
    // tslint:disable-next-line:max-line-length
    this.post.post = '<p style="margin-left: 140px;">Aterrorizante ser&aacute; o meu destino.&nbsp;</p><p style="margin-left: 140px;">Numa vida sem cria&ccedil;&atilde;o. Quando a inspira&ccedil;&atilde;o parar, estou certo que a morte me agarrar&aacute;.&nbsp;</p><p style="margin-left: 140px;">Mesmo que o meu corpo continue saud&aacute;vel. A minha alma apodrecer&aacute;. </p><p><br></p>';
    this.post.updatedDate = '27/08/2018';
    console.log(this.post);
  }

  onBtClick() {
    console.log(this.editorContent);
  }

}
