import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {Medication} from '../../dtos/Medication';
import {MedicationService} from '../../services/medication.service';
import {Effect} from '../../dtos/effect';
import {EntryService} from '../../services/entry.service';
import {EffectService} from '../../services/effect.service';
import {Entry} from '../../dtos/entry';
import {NgbCalendar, NgbDateStruct, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-add-entry',
  templateUrl: './add-entry.component.html',
  styleUrls: ['./add-entry.component.scss']
})
export class AddEntryComponent implements OnInit {
  @ViewChild('noMedicationsWindow')
  noMedicationsWindow: TemplateRef<any>;
  medicationsForUser: Medication[] = [];
  selectedMedications: Medication[] = [];
  selectedMedIds: number[] = [];
  effects: Effect[] = [];
  text = 'today';
  date: NgbDateStruct;
  hideOtherSideEffect = false;
  effectsForUser: string[] = [];
  customEffect: string = null;
  currentMed: Medication;
  entry: Entry;

  constructor(
    private medicationService: MedicationService,
    private entryService: EntryService,
    private effectService: EffectService,
    private calendar: NgbCalendar,
    private toastr: ToastrService,
    private modalService: NgbModal) { }

  ngOnInit(): void {
    this.getForUser();
    console.log(this.currentMed);
    this.getEffectsForMeds();
    console.log(this.date);
  }
  getForUser(): void {
    console.log('called');
    this.medicationService.getForUser().subscribe({
      next: data => {
        this.medicationsForUser = data;
        this.date = this.calendar.getToday();
        this.entry = new Entry(null, new Date(this.date.year, this.date.month - 1, this.date.day), [], this.selectedMedIds);
        this.currentMed = this.medicationsForUser[0];
        if (this.medicationsForUser.length <= 0) {
          // TODO: fix modal
          this.modalService.open(this.noMedicationsWindow, {backdrop: 'static', size: 'lg'});
        }
        console.log(this.medicationsForUser);
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
          this.toastr.success('Successful!', 'Entry was successfully created.');
        },
        error: err => {
          this.toastr.error('Error!', 'Please contact our administrator.');
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
  updateEntryModel(): void {
    this.entry.date = new Date(this.date.year, this.date.month - 1, this.date.day);
  }
}
