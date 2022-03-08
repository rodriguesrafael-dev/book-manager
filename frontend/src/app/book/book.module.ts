import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BookRoutingModule } from './book-routing.module';
import { BookFormComponent } from './book-form/book-form.component';
import { FormsModule } from '@angular/forms';
import { BookListComponent } from './book-list/book-list.component';


@NgModule({
  declarations: [
    BookFormComponent,
    BookListComponent
  ],
  imports: [
    CommonModule,
    BookRoutingModule,
    FormsModule
  ],
  exports: [
    BookFormComponent
  ]
})
export class BookModule { }
