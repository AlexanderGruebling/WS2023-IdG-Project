import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {Medication} from '../../dtos/Medication';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-add-med',
  templateUrl: './add-med.component.html',
  styleUrls: ['./add-med.component.scss']
})
export class AddMedComponent implements OnInit {
  @ViewChild('form') form: NgForm;
  @Output() medChangedEvent = new EventEmitter<Medication>();
  med: Medication = {
    name: null,
    dosage: null,
    frequency: null,
  };

  constructor() { }

  ngOnInit(): void {
    console.log('add-med loaded');
  }
  changeMed() {
    if (this.form.valid) {
      this.medChangedEvent.emit(this.med);
    }
  }
}
