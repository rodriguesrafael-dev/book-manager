import { Component, OnInit } from '@angular/core';
import { BookEntity } from '../book';
import { BookService } from '../service/book.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.scss']
})
export class BookListComponent implements OnInit {

  bookEntity: BookEntity[] = [];
  selectedBook: BookEntity;
  errorMessage: string;
  successMessage: string;

  constructor(
    private service: BookService,
    private router: Router) { }

  ngOnInit(): void {
    this.service.getBook().subscribe(response => this.bookEntity = response)
  }

  newBook() {
    this.router.navigate(['/book-form']);
  }

  deleteAsk(bookEntity: BookEntity) {
    this.selectedBook = bookEntity;
  }

  deleteBook() {
    this.service.delete(this.selectedBook).subscribe(
      response => {
        this.successMessage = 'Livro excluido com sucesso!'
        this.ngOnInit();
      },
      error => this.errorMessage = 'Erro ao tentar excluir o livro!'
    )
  }

}
