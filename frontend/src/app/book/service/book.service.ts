import { BookEntity } from './../book';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) { }

  save(bookEntity: BookEntity) : Observable<BookEntity> {
    return this.http.post<BookEntity>('http://localhost:8080/api/books', bookEntity);
  }

  update(bookEntity: BookEntity) : Observable<any> {
    return this.http.put<BookEntity>(`http://localhost:8080/api/books/${bookEntity.id}`, bookEntity);
  }

  delete(bookEntity: BookEntity) : Observable<any> {
    return this.http.delete<any>(`http://localhost:8080/api/books/${bookEntity.id}`);
  }

  getBook() : Observable<BookEntity[]> {
    return this.http.get<BookEntity[]>('http://localhost:8080/api/books');
  }

  getBookById(id: number) : Observable<BookEntity> {
    return this.http.get<any>(`http://localhost:8080/api/books/${id}`);
  }

}
