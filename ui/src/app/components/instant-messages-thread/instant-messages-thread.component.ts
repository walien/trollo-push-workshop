import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { InstantMessage } from '../../domain/instant-message.model';
import { RandomService } from '../../services/random.service';

@Component({
    selector: 'instant-messages-thread',
    templateUrl: './instant-messages-thread.component.html',
    styleUrls: ['./instant-messages-thread.component.scss']
})
export class InstantMessagesThreadComponent implements OnInit {

    @Input() public messages: InstantMessage[];
    @Output() public messagePosted: EventEmitter<InstantMessage> = new EventEmitter<InstantMessage>();

    public newMessageContent: string;
    private author: string;

    constructor(private randomService: RandomService) { }

    ngOnInit(): void {
        this.author = this.randomService.randomAuthorName();
    }

    public sendMessage(content: string) {
        if (content && content.length > 0) {
            this.messagePosted.emit({content: content, '@class': 'io.trollo.domain.InstantMessage', author: this.author});
            this.newMessageContent = '';
        }
    }
}