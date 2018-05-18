import { Component, EventEmitter, Input, Output } from '@angular/core';
import { InstantMessage } from '../../domain/instant-message.model';

@Component({
    selector: 'instant-messages-thread',
    templateUrl: './instant-messages-thread.component.html',
    styleUrls: ['./instant-messages-thread.component.scss']
})
export class InstantMessagesThreadComponent {

    @Input() public messages: InstantMessage[];
    @Output() public messagePosted: EventEmitter<InstantMessage> = new EventEmitter<InstantMessage>();

    public newMessageContent: string;

    public sendMessage(content: string) {
        this.messagePosted.emit({content: content, '@class': 'io.trollo.domain.InstantMessage'});
    }
}