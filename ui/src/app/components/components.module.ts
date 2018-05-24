import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardTileComponent } from './card-tile/card-tile.component';
import { InstantMessageTileComponent } from './instant-message-tile/instant-message-tile.component';
import { InstantMessagesThreadComponent } from './instant-messages-thread/instant-messages-thread.component';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { DashboardColumnComponent } from './dashboard-column/dashboard-column.component';

const components: any = [
    CardTileComponent,
    InstantMessageTileComponent,
    InstantMessagesThreadComponent,
    DashboardColumnComponent
];

@NgModule({
    imports: [
        CommonModule,
        BrowserModule,
        FormsModule
    ],
    declarations: [components],
    exports: [components]
})
export class ComponentsModule {
}
