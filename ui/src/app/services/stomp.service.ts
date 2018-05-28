import { Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { Subject } from 'rxjs/internal/Subject';
import { Observable } from 'rxjs/internal/Observable';

@Injectable()
export class StompService {

    public connect(config: StompConfig): Subject<StompConnection> {

        const connection = new Subject<StompConnection>();
        const socket = new SockJS(config.url);
        const stomp = Stomp.over(socket);
        stomp.debug = false;

        const headers = { login: config.login, passcode: config.password };
        const resolveFun = () => {
            console.log('stomp connection has been established');
            connection.next(new StompConnection(stomp));
        };
        stomp.heartbeat.outgoing = 0;
        stomp.heartbeat.incoming = 0;
        stomp.connect(headers, resolveFun, (err) => {
            console.log(err);
        });

        return connection;
    }
}

export class StompConnection {

    constructor(private stomp: any) { }

    public subscribeExchange(exchange: string, routingKey: string = '*'): StompSubscription {
        const responseObservable = new Subject<StompResponse>();
        console.log(`subscribing exchange ${exchange} (routingKey = ${routingKey})`);
        const subscription = this.stomp.subscribe(`/exchange/${exchange}/${routingKey}`, response => {
            const message = this.parseResponse(response);
            const headers = response.headers;
            responseObservable.next({ message, headers });
        }, { 'auto-delete': true });
        return new StompSubscription(subscription, responseObservable.asObservable());
    }

    public send(exchange: string, message: any, routingKey: string = '*'): void {
        const messageAsText = JSON.stringify({ payload: message, publisher: 'wms.frontend' });
        this.stomp.send(`/exchange/${exchange}/${routingKey}`, {}, messageAsText);
    }

    private parseResponse(response) {
        if (response.headers['Content-Type'] === 'application/json') {
            return JSON.parse(response.body);
        }
        throw new Error('Unable to parse response. Supported types [application/json]');
    }

    public disconnect() {
        return new Promise((resolve, reject) => {
            console.log('... stomp disconnected');
            this.stomp.disconnect(() => resolve());
        });
    }
}

export interface StompResponse {
    message: any;
    headers: any[];
}

export class StompSubscription {

    private subscription: any;
    response: Observable<StompResponse>;

    constructor(subscription: any, response: Observable<StompResponse>) {
        this.subscription = subscription;
        this.response = response;
    }

    public unsubscribe() {
        console.log('... subscription destroyed');
        this.subscription.unsubscribe();
    }
}

export interface StompConfig {
    enabled: boolean;
    url: string;
    login: string;
    password: string;
}
