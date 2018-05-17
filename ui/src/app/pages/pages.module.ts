import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ComponentsModule } from '../components/components.module';

const components: any[] = [
    DashboardComponent
];

@NgModule({
    imports: [
        CommonModule,
        ComponentsModule
    ],
    declarations: [components],
    exports: [components]
})
export class PagesModule {
}
