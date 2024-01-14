import {environment} from '../../environments/environment';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Effect} from '../dtos/effect';

const baseUri = environment.backendUrl + '/api/v1/effect';

@Injectable({
  providedIn: 'root'
})
export class EffectService {
  constructor(
    private http: HttpClient
  ) {}
  create(effect: Effect): Observable<Effect> {
    return this.http.post<Effect>(baseUri, effect);
  }
  getByMedId(medId: number): Observable<Effect[]> {
    return this.http.get<Effect[]>(`${baseUri}/${medId}`);
  }
}
