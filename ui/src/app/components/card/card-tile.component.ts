import { Component, Input } from '@angular/core';
import { Card } from '../../domain/card.model';

@Component({
    selector: 'card-tile',
    templateUrl: './card-tile.component.html',
    styleUrls: ['./card-tile.component.scss']
})
export class CardTileComponent {

    @Input() card: Card;
}
