import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Card } from '../domain/card.model';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs/internal/Observable';
import { map } from 'rxjs/operators';
import { State } from '../domain/state.model';

@Injectable()
export class CardService {

    private baseUrl: string = environment.backendApiBaseUrl + '/api/card';
    private workflow: State[] = ['TODO', 'IN_PROGRESS', 'VERIFIED', 'DELIVERED'];

    constructor(private http: HttpClient) { }

    public fetchAll(): Observable<Card[]> {
        return this.http
            .get(this.baseUrl, {withCredentials: true})
            .pipe(map(result => result as Card[]));
    }

    public nextState(card: Card) {
        const index = this.workflow.indexOf(card.state) + 1;
        if (index < this.workflow.length) {
            return this.changeState(card, this.workflow[index]);
        }
        return Observable.create();
    }

    public previousState(card: Card) {
        const index = this.workflow.indexOf(card.state) - 1;
        if (index >= 0) {
            return this.changeState(card, this.workflow[index]);
        }
        return Observable.create();
    }

    private changeState(card: Card, newState: State): Observable<Card> {
        return this.http
            .put(`${this.baseUrl}/${card._id}/state/${newState}`, {withCredentials: true})
            .pipe(map(result => result as Card));
    }
}
