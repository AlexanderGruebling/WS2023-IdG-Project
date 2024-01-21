import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Medication, MedicationWithEffects} from '../../../dtos/Medication';
import {Effect} from '../../../dtos/effect';
import {Entry} from '../../../dtos/entry';

@Component({
  selector: 'app-entry-for-med',
  templateUrl: './entry-for-med.component.html',
  styleUrls: ['./entry-for-med.component.scss']
})
export class EntryForMedComponent implements OnInit, OnChanges {
  @Input() entry: Entry;
  @Input() medicationsForUser: Medication[];
  @Output() effectChanges = new EventEmitter<MedicationWithEffects>();
  selectedMed: Medication;
  effectNames: string[] = [];
  effects: Effect[] = [];
  hideOtherSideEffect = false;
  customEffect: string;

  constructor() { }

  ngOnInit(): void {
    this.selectedMed = this.medicationsForUser[5];
  }
  ngOnChanges(changes: SimpleChanges) {
    if (this.medicationsForUser.length <= 0) {
      this.selectedMed = new Medication(5, 'None', 0, 0);
    } else {
      this.selectedMed = this.medicationsForUser[5];
    }
  }

  addEffect(effect: string): void {
    if (effect === 'None') {
      this.effectNames = [];
      return;
    }
    if (this.effectNames.indexOf(effect) > -1) {
      this.effectNames.splice(this.effectNames.indexOf(effect), 1);
      return;
    }
    this.effectNames.push(effect);
    this.updateParent();
  }
  toggleOtherSideEffect(): void {
    this.hideOtherSideEffect = !this.hideOtherSideEffect;
  }
  addCustomEffect(): void {
    this.effectNames.push(this.customEffect);
    this.customEffect = null;
  }
  addEffectToEntry(newEffect: Effect) {
    this.effects.push(newEffect);
    this.updateParent();
  }
  updateParent() {
    this.effectChanges.emit(new MedicationWithEffects(this.selectedMed, this.effects));
  }
}
