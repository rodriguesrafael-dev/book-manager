import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';
import { UsersFormComponent } from './users-form/users-form.component';


@NgModule({
  declarations: [
    UsersFormComponent
  ],
  imports: [
    CommonModule,
    UsersRoutingModule
  ],
  exports: [
    UsersFormComponent
  ]
})
export class UsersModule { }
