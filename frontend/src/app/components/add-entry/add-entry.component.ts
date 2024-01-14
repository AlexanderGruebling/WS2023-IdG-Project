import { Component, OnInit } from '@angular/core';
import {Medication} from '../../dtos/Medication';
import {MedicationService} from '../../services/medication.service';
import {Effect} from '../../dtos/effect';
import {EntryService} from '../../services/entry.service';
import {EffectService} from '../../services/effect.service';
import {Entry} from '../../dtos/entry';

@Component({
  selector: 'app-add-entry',
  templateUrl: './add-entry.component.html',
  styleUrls: ['./add-entry.component.scss']
})
export class AddEntryComponent implements OnInit {
  medicationsForUser: Medication[] = [];
  selectedMedications: Medication[] = [];
  selectedMedIds: number[] = [];
  effects: Effect[] = [];
  date: string = new Date().toISOString();
  hideOtherSideEffect = false;
  effectsForUser: string[] = [];
  customEffect: string = null;
  currentMed: Medication;
  entry: Entry = new Entry(null, new Date(this.date), [], this.selectedMedIds);

  constructor(
    private medicationService: MedicationService,
    private entryService: EntryService,
    private effectService: EffectService) { }

  ngOnInit(): void {
    this.getForUser();
    this.getEffectsForMeds();
    console.log(this.date);
  }
  getForUser(): void {
    console.log('called');
    this.medicationService.getForUser().subscribe({
      next: data => {
        console.log(data);
      this.medicationsForUser = data;
      this.currentMed = this.medicationsForUser[0];
      console.log(this.medicationsForUser);
    },
    error: err => {
        console.log(err);
    }
    });
  }
  getEffectsForMeds(): void {
    this.medicationsForUser.forEach(x => this.effectService.getByMedId(x.medId).subscribe({
      next: data => {
        this.effects = data;
        console.log(this.effects);
      },
      error: err => {
        console.log(err);
      }
    }));
  }
  toggleOtherSideEffect(): void {
    this.hideOtherSideEffect = !this.hideOtherSideEffect;
  }
  addEffect(effect: string): void {
    if (effect === 'None') {
      this.effectsForUser = [];
      return;
    }
    if (this.effectsForUser.indexOf(effect) > -1) {
      this.effectsForUser.splice(this.effectsForUser.indexOf(effect), 1);
      return;
    }
    this.effectsForUser.push(effect);
  }
  addCustomEffect(): void {
    this.effectsForUser.push(this.customEffect);
    this.customEffect = null;
  }
  addEffectToEntry(newEffect: Effect) {
    this.entry.effects.push(newEffect);
  }
  addEntry(){
    console.log(this.entry);
    this.entryService.create(this.entry).subscribe({
        next: data => {
          console.log('Successful');
        },
        error: err => {
          console.log(err);
        }
      }
    );
  }
  updateCheckedMeds(medication: Medication) {
    console.log(medication.medId);
    const index = this.selectedMedications.indexOf(medication);
    if (index > -1) {
      this.selectedMedications.splice(index, 1);
    } else {
      this.selectedMedications.push(medication);
    }
    this.entry.medIds = this.selectedMedications.map(x => x.medId);
  }
}
