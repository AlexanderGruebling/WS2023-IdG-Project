import {PlotData} from './plotData';

export class Effect {
  constructor(
    public name: string,
    public description: string,
    public intensity: number,
    public desired: boolean,
    public medId: number,
  ) {
  }
}

export class EffectWithEntries {
  constructor(
    public effectName: string,
    public entriesForPlot: PlotData[],
  ) {
  }
}
