import {Effect} from './effect';

export class Medication {
  constructor(
    public medId: number,
    public name: string,
    public dosage: number,
    public frequency: number
  ) {}
}

/**
 * Not a real dto, only to be used as a wrapper in the frontend!
 */
export class MedicationWithEffects {
  constructor(
    public selectedMed: Medication,
    public observedEffects: Effect[]
  ) {
  }
}
