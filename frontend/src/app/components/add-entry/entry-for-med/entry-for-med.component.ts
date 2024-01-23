import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Medication, MedicationWithEffects} from '../../../dtos/Medication';
import {Effect} from '../../../dtos/effect';
import {Entry} from '../../../dtos/entry';
import {EffectService} from '../../../services/effect.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-entry-for-med',
  templateUrl: './entry-for-med.component.html',
  styleUrls: ['./entry-for-med.component.scss']
})
export class EntryForMedComponent implements OnInit, OnChanges {
  @Input() entry: Entry;
  @Input() medicationsForUser: Medication[];
  @Output() effectChanges = new EventEmitter<MedicationWithEffects>();
  @Output() clearAll = new EventEmitter();
  selectedMed: Medication;
  effects: Effect[] = [];
  effectsForUser: string[] = [];
  numberOfEffects = 1;
  medSelected = false;
  resetEffects = false;

  constructor(
    private effectService: EffectService,
    private toastrService: ToastrService,
  ) { }

  ngOnInit(): void {
    this.selectedMed = this.medicationsForUser[5];
    this.getEffectsForUser();
  }
  ngOnChanges(changes: SimpleChanges) {
    if (this.medicationsForUser.length <= 0) {
      this.selectedMed = new Medication(5, 'None', 0, 0);
    } else {
      const noneMedIndex = this.medicationsForUser.findIndex(medication => medication.name === 'None');
      this.selectedMed = this.medicationsForUser[noneMedIndex];
    }
  }
  getEffectsForUser(): void {
    this.effectService.getAllEffectNames().subscribe({
      next: value => {
        if (value.length === 0) {
          this.effectsForUser.push('Other');
        } else {
          this.effectsForUser = value;
          console.log(this.effectsForUser);
        }
      },
      error: err => this.toastrService.error('Error!', 'Could not fetch your effects.')
    });
  }
  addEffect(): void {
    this.numberOfEffects++;
    this.medSelected = true;
  }
  addEffectToEntry(newEffect: Effect) {
    this.effects.push(newEffect);
    this.updateParent();
  }
  updateParent() {
    this.effectChanges.emit(new MedicationWithEffects(this.selectedMed, this.effects));
  }
  emitClearAllEvent() {
    this.clearAll.emit();
    this.numberOfEffects = 1;
    this.resetEffects = true;
  }
}
