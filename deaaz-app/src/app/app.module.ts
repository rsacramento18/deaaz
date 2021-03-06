import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import { FroalaEditorModule, FroalaViewModule } from 'angular-froala-wysiwyg';
import {ReactiveFormsModule} from '@angular/forms';

import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { PostComponent } from './post/post.component';
import { SafeHtmlPipe } from './safe-html.pipe';
import { BlogComponent } from './blog/blog.component';
import { SobreComponent } from './sobre/sobre.component';
import { ContactosComponent } from './contactos/contactos.component';
import { EscritorComponent } from './escritor/escritor.component';
import {AngularFontAwesomeModule} from 'angular-font-awesome';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    PostComponent,
    SafeHtmlPipe,
    BlogComponent,
    SobreComponent,
    ContactosComponent,
    EscritorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    ReactiveFormsModule,
    TranslateModule.forRoot({
        loader: {
            provide: TranslateLoader,
            useFactory: HttpLoaderFactory,
            deps: [HttpClient]
        }
    }),
    FroalaEditorModule.forRoot(),
    FroalaViewModule.forRoot(),
    AngularFontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

export function HttpLoaderFactory(http: HttpClient) {
    return new TranslateHttpLoader(http);
}
