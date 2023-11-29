import { Component, OnInit } from '@angular/core';
import {Medication} from '../../dtos/Medication';
import {MedicationService} from '../../services/medication.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-add-meds',
  templateUrl: './add-meds.component.html',
  styleUrls: ['./add-meds.component.scss']
})
export class AddMedsComponent implements OnInit {
  numberOfMeds = 1;
  meds: Set<Medication>;
  validateChildren = false;

  constructor(
    private service: MedicationService,
    private notification: ToastrService,
  ) { }

  ngOnInit(): void {
    console.log('add-meds loaded');
    this.meds = new Set<Medication>();
  }

  updateMeds(med: Medication): void {
    this.meds.add(med);
    console.log(this.meds);
  }

  addMeds(): void {
    this.meds.forEach(med => this.service.create(med).subscribe({
      next: data => {
        console.log(`${med.name} successfully created`);
      },
      error: err => {
        console.error('Error creating med', err);
        this.notification.error(`Could not create Medication ${med.name}!` );
      }
    }
    ));
  }
  addAnotherMedFE(): void {
    this.numberOfMeds++;
    this.validateChildren = true;
  }

}
