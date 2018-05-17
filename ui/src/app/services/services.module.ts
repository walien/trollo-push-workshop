import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardService } from './card.service';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
    imports: [
        CommonModule,
        HttpClientModule
    ],
    declarations: [],
    providers: [
        CardService
    ]
})
export class ServicesModule {
}
