import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardService } from './card.service';
import { HttpClientModule } from '@angular/common/http';
import { InstantMessageService } from './instant-message.service';
import { RandomService } from './random.service';

@NgModule({
    imports: [
        CommonModule,
        HttpClientModule
    ],
    declarations: [],
    providers: [
        RandomService,
        CardService,
        InstantMessageService
    ]
})
export class ServicesModule {
}
