import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardService } from './card.service';
import { HttpClientModule } from '@angular/common/http';
import { StompService } from './stomp.service';
import { InstantMessageService } from './instant-message.service';

@NgModule({
    imports: [
        CommonModule,
        HttpClientModule
    ],
    declarations: [],
    providers: [
        StompService,
        CardService,
        InstantMessageService
    ]
})
export class ServicesModule {
}
