import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { BlogComponent } from './blog/blog.component';
import { SobreComponent } from './sobre/sobre.component';
import { ContactosComponent } from './contactos/contactos.component';
import { EscritorComponent } from './escritor/escritor.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'blog', component: BlogComponent },
  { path: 'sobre', component: SobreComponent },
  { path: 'contactos', component: ContactosComponent },
  { path: 'escritor', component: EscritorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
