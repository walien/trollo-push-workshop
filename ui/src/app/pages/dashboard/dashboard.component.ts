import { Component, OnInit } from '@angular/core';
import { CardService } from '../../services/card.service';
import * as _ from 'lodash';
import { map } from 'rxjs/operators';
import { InstantMessageService } from '../../services/instant-message.service';
import { InstantMessage } from '../../domain/instant-message.model';
import { StompService } from '../../services/stomp.service';
import { Card } from '../../domain/card.model';

@Component({
    selector: 'dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

    public cards: { [state: string]: Card[] };
    public messages: InstantMessage[];

    constructor(private cardService: CardService,
                private instantMessageService: InstantMessageService,
                private stompService: StompService) {}

    ngOnInit(): void {
        this.subscribeToPushServices();
        this.fetchCards();
        this.fetchMessages();
    }

    private subscribeToPushServices() {
        const config = {
            url: 'http://127.0.0.1:15674/stomp',
            login: 'guest',
            password: 'guest',
            enabled: true
        };
        this.stompService.connect(config).subscribe(connection => {
            connection.subscribeExchange('trollo.cards').response.subscribe(payload => this.fetchCards());
            connection.subscribeExchange('trollo.messages').response.subscribe(payload => this.fetchMessages());
        });
    }

    private fetchCards() {
        this.cardService.fetchAll()
            .pipe(map(cards => _.groupBy(cards, card => card.state)))
            .subscribe(cards => this.cards = cards);
    }

    private fetchMessages() {
        this.instantMessageService.fetchAll().subscribe(messages => this.messages = messages);
    }

    public sendMessage(message: InstantMessage) {
        this.instantMessageService.send(message).subscribe(() => {
            // nothing to do
        });
    }
}
