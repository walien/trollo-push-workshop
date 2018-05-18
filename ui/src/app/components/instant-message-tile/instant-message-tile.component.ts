import { Component, Input } from '@angular/core';
import { InstantMessage } from '../../domain/instant-message.model';

@Component({
    selector: 'instant-messages-tile',
    templateUrl: './instant-message-tile.component.html',
    styleUrls: ['./instant-message-tile.component.scss']
})
export class InstantMessageTileComponent {

    @Input() message: InstantMessage;
}
