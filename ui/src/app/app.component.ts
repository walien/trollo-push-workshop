import { Component, OnInit } from '@angular/core';
import { PushService } from './services/push.service';

@Component({
    selector: 'app',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

    constructor(private pushService: PushService) {}

    ngOnInit(): void {
        this.pushService.enableServices();
    }
}
