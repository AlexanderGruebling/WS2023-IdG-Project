import {Effect} from './effect';
import {DosagePlotData} from './dosagePlotData';

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

/**
 * Not a real dto, only to be used as a wrapper in the frontend!
 */
export class MedicationWithEntries {
  constructor(
    public selectedMed: Medication,
    public entriesForPlot: DosagePlotData[]
  ) {
  }
}
