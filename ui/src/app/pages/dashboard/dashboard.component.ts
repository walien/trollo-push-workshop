import { Component, OnInit } from '@angular/core';
import { CardService } from '../../services/card.service';
import * as _ from 'lodash';
import { map } from 'rxjs/operators';
import { PushService } from '../../services/push.service';
import { InstantMessageService } from '../../services/instant-message.service';
import { InstantMessage } from '../../domain/instant-message.model';

@Component({
    selector: 'dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

    public cards: { [index: string]: any };
    public messages: InstantMessage[];

    constructor(private cardService: CardService,
                private instantMessageService: InstantMessageService,
                private pushService: PushService) {
    }

    ngOnInit(): void {
        this.fetchCards();
        this.fetchMessages();
        this.pushService.cardUpdated.subscribe(card => this.fetchCards());
        this.pushService.instantMessagePosted.subscribe(message => this.fetchMessages());
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
