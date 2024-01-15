import {environment} from '../../environments/environment';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Entry} from '../dtos/entry';

const baseUri = environment.backendUrl + '/api/v1/entry';

@Injectable({
  providedIn: 'root'
})
export class EntryService {
  constructor(
    private http: HttpClient
  ) {}
  create(entry: Entry): Observable<Entry> {
    return this.http.post<Entry>(baseUri, entry);
  }
  getForUser(): Observable<Entry[]> {
    return this.http.get<Entry[]>(baseUri);
  }
}
