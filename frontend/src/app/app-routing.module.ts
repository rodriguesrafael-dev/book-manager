import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { UsersFormComponent } from './users/users-form/users-form.component';
import { BookFormComponent } from './book/book-form/book-form.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'userform', component: UsersFormComponent },
  { path: 'book', component: BookFormComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
