import { State } from './state.model';

export interface Card {

    '@class'?: string;
    _id: string;
    title: string;
    body: string;
    state: State;
}