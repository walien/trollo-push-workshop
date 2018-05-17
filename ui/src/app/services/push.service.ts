import { Injectable } from '@angular/core';
import { StompConnection, StompService, StompSubscription } from './stomp.service';
import { Subject } from 'rxjs/internal/Subject';
import { Card } from '../domain/card.model';
import { InstantMessage } from '../domain/instant-message.model';

@Injectable()
export class PushService {

    private stompSubscriptions: StompSubscription[] = [];
    public cardUpdated = new Subject<Card>();
    public instantMessagePosted = new Subject<InstantMessage>();

    constructor(private stompService: StompService) { }

    public enableServices() {
        this.connectToStomp().subscribe(connection => {
            this.subscribeExchange(connection, 'trollo.cards', '*')
                .response
                .subscribe(value => this.cardUpdated.next(value as Card));
            this.subscribeExchange(connection, 'trollo.messages', '*')
                .response
                .subscribe(value => this.instantMessagePosted.next(value as InstantMessage));
        });
    }

    public disableServices() {
        this.stompSubscriptions.forEach(subscription => subscription.unsubscribe());
    }

    private connectToStomp(): Subject<StompConnection> {
        const config = {
            url: 'http://127.0.0.1:15674/stomp',
            login: 'guest',
            password: 'guest',
            enabled: true
        };
        return this.stompService.connect(config);
    }

    private subscribeExchange(connection: StompConnection, exchangeName: string, routingKey: string) {
        const stompSubscription = connection.subscribe(exchangeName, routingKey);
        this.stompSubscriptions.push(stompSubscription);
        return stompSubscription;
    }
}
