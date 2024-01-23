import {Component, OnInit} from '@angular/core';
import {Medication, MedicationWithEffects} from '../../dtos/Medication';
import {MedicationService} from '../../services/medication.service';
import {Effect} from '../../dtos/effect';
import {EntryService} from '../../services/entry.service';
import {EffectService} from '../../services/effect.service';
import {Entry} from '../../dtos/entry';
import {NgbCalendar, NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-entry',
  templateUrl: './add-entry.component.html',
  styleUrls: ['./add-entry.component.scss']
})
export class AddEntryComponent implements OnInit {
  medicationsForUser: Medication[] = [];
  selectedMedIds: number[] = [];
  effects: Effect[] = [];
  date: NgbDateStruct;
  entry: Entry;
  numberOfMeds = 1;

  constructor(
    private medicationService: MedicationService,
    private entryService: EntryService,
    private effectService: EffectService,
    private calendar: NgbCalendar,
    private toastr: ToastrService,
    private router: Router) { }

  ngOnInit(): void {
    this.getForUser();
    this.getEffectsForMeds();
    console.log(this.calendar.getToday());
  }
  getForUser(): void {
    this.medicationService.getForUser().subscribe({
      next: data => {
        this.medicationsForUser = data;
        this.date = this.calendar.getToday();
        this.entry = new Entry(null, new Date(this.date.year, this.date.month - 1, this.date.day + 1), [], this.selectedMedIds);
    },
    error: err => {
        this.toastr.error('Error!', 'Please contact our administrator: ' + err.errors.toString());
    }
    });
  }
  getEffectsForMeds(): void {
    this.medicationsForUser.forEach(x => this.effectService.getByMedId(x.medId).subscribe({
      next: data => {
        this.effects = data;
      },
      error: err => {
        this.toastr.error('Error!', 'Could not fetch effects for medications! Please contact our administrator.');
      }
    }));
  }
  addEntry(){
    console.log(this.entry);
    this.entryService.create(this.entry).subscribe({
        next: data => {
          this.toastr.success('Successful!', 'Entry was successfully created.');
        },
        error: err => {
          this.toastr.error('Error!', 'Please contact our administrator.');
        }
      }
    );
    this.router.navigate(['']);
  }
  addMed() {
    this.numberOfMeds += 1;
  }
  updateDateEntryModel(): void {
    this.entry.date = new Date(this.date.year, this.date.month - 1, this.date.day + 1);
  }
  updateEntryModel(medicationWithEffects: MedicationWithEffects) {
    this.entry.medIds.push(medicationWithEffects.selectedMed.medId);
    this.entry.effects = this.entry.effects.concat(medicationWithEffects.observedEffects);
    this.entry.medIds = this.getDistinct(this.entry.medIds);
    this.entry.effects = this.getDistinct(this.entry.effects);
  }
  getDistinct(array: any[]): any[] {
    return [...new Set(array)];
  }
}
