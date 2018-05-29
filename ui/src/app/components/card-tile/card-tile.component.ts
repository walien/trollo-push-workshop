import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Card } from '../../domain/card.model';
import { CardService } from '../../services/card.service';

@Component({
    selector: 'card-tile',
    templateUrl: './card-tile.component.html',
    styleUrls: ['./card-tile.component.scss']
})
export class CardTileComponent {

    @Input() card: Card;
    @Output() cardChange = new EventEmitter<Card>();

    constructor(private cardService: CardService) {}

    public next(card: Card) {
        this.cardService.nextState(card).subscribe(card => this.cardChange.emit(card));
    }

    public previous(card: Card) {
        this.cardService.previousState(card).subscribe(card => this.cardChange.emit(card));
    }
}
