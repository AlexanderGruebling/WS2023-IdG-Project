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
  selectedMed: Medication;
  effects: Effect[] = [];
  effectsForUser: string[] = [];
  numberOfEffects = 1;
  medSelected = false;

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
      this.selectedMed = this.medicationsForUser[5];
    }
  }
  getEffectsForUser(): void {
    this.effectService.getAllEffectNames().subscribe({
      next: value => this.effectsForUser = value,
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
}
