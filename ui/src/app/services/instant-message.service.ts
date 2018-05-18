import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs/internal/Observable';
import { InstantMessage } from '../domain/instant-message.model';

@Injectable()
export class InstantMessageService {

    private baseUrl: string = environment.backendApiBaseUrl + '/api/message';

    constructor(private http: HttpClient) { }

    public fetchAll(): Observable<InstantMessage[]> {
        return this.http
            .get(this.baseUrl, {withCredentials: true})
            .pipe(map(result => result as InstantMessage[]));
    }

    public send(message: InstantMessage): Observable<InstantMessage> {
        return this.http
            .post(this.baseUrl, message, {withCredentials: true})
            .pipe(map(result => result as InstantMessage));
    }
}
