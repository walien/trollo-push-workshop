import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { NgModule } from '@angular/core';
import { PagesModule } from './pages/pages.module';

const routes: Routes = [
    { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
    { path: 'dashboard', component: DashboardComponent },
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes, { useHash: true }),
        PagesModule
    ],
    exports: [
        RouterModule
    ],
    providers: []
})
export class RoutingModule {

}
