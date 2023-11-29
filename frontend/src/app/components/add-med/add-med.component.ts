import {Component, EventEmitter, Input, OnInit, OnChanges, Output, ViewChild} from '@angular/core';
import {Medication} from '../../dtos/Medication';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-add-med',
  templateUrl: './add-med.component.html',
  styleUrls: ['./add-med.component.scss']
})
export class AddMedComponent implements OnInit, OnChanges {
  @ViewChild('form') form: NgForm;
  @Input() updateValidationStatus = false;
  @Output() medChangedEvent: EventEmitter<Medication> = new EventEmitter<Medication>();
  med: Medication = {
    medId: null,
    name: null,
    dosage: null,
    frequency: null,
  };

  constructor() { }

  ngOnInit(): void {
    console.log('add-med loaded');
  }
  ngOnChanges(){
    if(this.form !== undefined && !this.form.untouched) {
      this.form.ngSubmit.emit();
    }
  }
  changeMed(): void {
    if (this.form.valid) {
      this.medChangedEvent.emit(this.med);
    }
  }
}
