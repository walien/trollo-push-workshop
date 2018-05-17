import { Component, OnInit } from '@angular/core';
import { CardService } from '../../services/card.service';
import * as _ from 'lodash';
import { map } from 'rxjs/operators';
import { StompService } from '../../services/stomp.service';
import { PushService } from '../../services/push.service';

@Component({
    selector: 'dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

    public cards: { [index: string]: any };

    constructor(private cardService: CardService, private pushService: PushService) { }

    ngOnInit(): void {
        this.fetchCards();
        this.pushService.cardUpdated.subscribe(card => this.fetchCards());
    }

    private fetchCards() {
        this.cardService.fetchAll()
            .pipe(map(cards => _.groupBy(cards, card => card.state)))
            .subscribe(cards => this.cards = cards);
    }
}
