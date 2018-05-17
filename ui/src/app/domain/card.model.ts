import { State } from './state.model';

export interface Card {

    _id: string;
    title: string;
    body: string;
    state: State;
}