import {Effect} from './effect';

export class Entry {
  constructor(
    public entryId: number,
    public date: Date,
    public effects: Effect[],
    public medIds: number[],
  ) {
  }
}
