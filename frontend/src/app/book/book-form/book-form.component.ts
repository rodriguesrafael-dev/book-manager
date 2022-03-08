import { Component, OnInit } from '@angular/core';
import { BookEntity } from '../book';
import { BookService } from '../service/book.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.scss']
})
export class BookFormComponent implements OnInit {

  bookEntity: BookEntity;
  saveUpdateSuccess: boolean = false;
  errorsToSaveUpdate: String[];
  id: number;

  constructor(
    private service: BookService,
    private router: Router,
    private activatedRoute: ActivatedRoute) {
    this.bookEntity = new BookEntity();
  }

  ngOnInit(): void {
    let params : Observable<Params> = this.activatedRoute.params;
    params.subscribe(urlParams => {
      this.id = urlParams['id'];

      if (this.id) {
        this.service.getBookById(this.id)
        .subscribe(response => this.bookEntity = response,
          errorResponse => this.bookEntity = new BookEntity())
      }
    })
  }

  onSubmit() {
    if (this.id) {
      this.service.update(this.bookEntity)
      .subscribe(response => {
        this.saveUpdateSuccess = true;
        this.errorsToSaveUpdate = [];
      }, errorResponse => {
        this.errorsToSaveUpdate = ['Erro ao atualizar']
      })
    } else {
      this.service.save(this.bookEntity).subscribe( response => {
        this.saveUpdateSuccess = true;
        this.errorsToSaveUpdate = [];
        this.bookEntity = response;
      }, errorResponse => {
        this.saveUpdateSuccess = false;
        this.errorsToSaveUpdate = errorResponse.error.errors;
      })
    }

  }

  backToList() {
    this.router.navigate(['/book-list'])
  }

}
