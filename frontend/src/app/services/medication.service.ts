import {environment} from '../../environments/environment';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Medication} from '../dtos/Medication';
import {DosagePlotData} from '../dtos/dosagePlotData';

const baseUri = environment.backendUrl + '/api/v1/medication';

@Injectable({
  providedIn: 'root'
})
export class MedicationService {
  constructor(
    private http: HttpClient
  ) {}

  getById(id: number): Observable<Medication> {
    return this.http.get<Medication>(`${baseUri}/${id}`);
  }

  create(med: Medication): Observable<Medication> {
    return this.http.post<Medication>(baseUri, med);
  }
  getForUser(): Observable<Medication[]> {
    return this.http.get<Medication[]>(baseUri);
  }
  delete(id: number): Observable<void> {
    return this.http.delete<void>(baseUri + '/' + id);
  }
  getForName(name: string): Observable<DosagePlotData[]> {
    return this.http.get<DosagePlotData[]>(`${baseUri}/dosage/${name}`);
  }
}
