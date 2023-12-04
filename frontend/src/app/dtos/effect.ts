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
