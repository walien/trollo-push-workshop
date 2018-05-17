import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardTileComponent } from './card/card-tile.component';

const components: any = [
    CardTileComponent
];

@NgModule({
    imports: [
        CommonModule
    ],
    declarations: [components],
    exports: [components]
})
export class ComponentsModule {
}
