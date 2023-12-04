import { Component, OnInit } from '@angular/core';
import {Medication} from '../../dtos/Medication';
import {MedicationService} from '../../services/medication.service';
import {Effect} from '../../dtos/effect';

@Component({
  selector: 'app-add-entry',
  templateUrl: './add-entry.component.html',
  styleUrls: ['./add-entry.component.scss']
})
export class AddEntryComponent implements OnInit {
  medicationsForUser: Medication[] = [];
  date: string = new Date().toISOString();
  hideOtherSideEffect = false;
  effectsForUser: string[] = [];
  customEffect: string = null;
  constructor(private medicationService: MedicationService) { }

  ngOnInit(): void {
    this.getForUser();
    console.log(this.date);
  }
  getForUser(): void {
    console.log('called');
    this.medicationService.getForUser().subscribe({
      next: data => {
      this.medicationsForUser = data;
      console.log(this.medicationsForUser);
    },
    error: err => {
        console.log(err);
    }
    });
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
}
