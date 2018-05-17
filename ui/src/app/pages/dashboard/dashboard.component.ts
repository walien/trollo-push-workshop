import { Component, OnInit } from '@angular/core';
import { CardService } from '../../services/card.service';
import * as _ from 'lodash';
import { map } from 'rxjs/operators';

@Component({
    selector: 'dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

    public cards: { [index: string]: any };

    constructor(private cardService: CardService) {}

    ngOnInit(): void {
        this.cardService.fetchAll()
            .pipe(map(cards => _.groupBy(cards, card => card.state)))
            .subscribe(cards => this.cards = cards);
    }
}
