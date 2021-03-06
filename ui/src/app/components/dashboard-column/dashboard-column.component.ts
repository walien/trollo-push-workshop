import { Component, Input } from '@angular/core';
import { Card } from '../../domain/card.model';

@Component({
    selector: 'dashboard-column',
    templateUrl: './dashboard-column.component.html',
    styleUrls: ['./dashboard-column.component.scss']
})
export class DashboardColumnComponent {

    @Input() public cards: Card[];
    @Input() public title: string;

}
