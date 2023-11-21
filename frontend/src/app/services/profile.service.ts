import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../global/globals';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {Registration} from '../dtos/registration';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  private profileBaseUri: string = this.globals.backendUri + '/profile';

  constructor(private httpClient: HttpClient, private globals: Globals) {
  }

  /**
   * Delete an existing user.
   */
  deleteUser(): Observable<void> {
    return this.httpClient.delete<void>(this.profileBaseUri);
  }

  /**
   * Get the current user information.
   */
  getUser(): Observable<Registration>{
    return this.httpClient.get<Registration>(this.profileBaseUri);
  }

  private setToken(authResponse: string) {
    localStorage.setItem('authToken', authResponse);
  }
}
