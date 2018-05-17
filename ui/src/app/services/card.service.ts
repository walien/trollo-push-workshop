import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Card } from '../domain/card.model';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs/internal/Observable';
import { map } from 'rxjs/operators';

@Injectable()
export class CardService {

    private baseUrl: string = environment.backendApiBaseUrl + '/api/card';

    constructor(private http: HttpClient) { }

    public fetchAll(): Observable<Card[]> {
        return this.http
            .get(this.baseUrl, {withCredentials: true})
            .pipe(map(result => result as Card[]));
    }
}
