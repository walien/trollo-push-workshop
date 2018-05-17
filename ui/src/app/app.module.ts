import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { PagesModule } from './pages/pages.module';
import { ComponentsModule } from './components/components.module';
import { RoutingModule } from './routing.module';
import { ServicesModule } from './services/services.module';

@NgModule({
    imports: [
        BrowserModule,
        RoutingModule,
        PagesModule,
        ComponentsModule,
        ServicesModule
    ],
    providers: [],
    declarations: [AppComponent],
    bootstrap: [AppComponent]
})
export class AppModule {
}
