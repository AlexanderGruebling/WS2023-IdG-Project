import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Registration} from '../../dtos/registration';
import {ProfileService} from '../../services/profile.service';
import {EntryService} from '../../services/entry.service';
import {ToastrService} from 'ngx-toastr';
import {MedicationService} from '../../services/medication.service';
import {Effect} from '../../dtos/effect';
import {Entry} from '../../dtos/entry';
import {Medication} from '../../dtos/Medication';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  profile: Registration = {
    username: '',
    email: '',
    password: ''
  };
  entries: Entry[] = [];
  medications: Medication[] = [];

  constructor(public authService: AuthService,
              public profileService: ProfileService,
              private entryService: EntryService,
              private notification: ToastrService,
              private medicationService: MedicationService) { }

  ngOnInit() {
    if (this.authService.isLoggedIn()){
      this.profileService.getUser().subscribe(data => {
        this.profile.username = data.username;
      },);
      this.fetchEntries();
      this.fetchMeds();
    }
  }



  fetchEntries(): void {
    this.entryService.getForUser().subscribe({
      next: data => {
        this.entries = data.slice(0,5);
        console.log(data);
      }, error: err => {
        console.log(err);
      }
    });
  }

  deleteEntry(id): void {
    this.entryService.delete(id).subscribe({
      next: () => {
        this.notification.success(`Medication successfully deleted`);
        this.fetchEntries();
      },
      error: error => {
        console.error('Error deleting Med: ', error);
        this.notification.error(error.error.error, error.error.message);
      },
    });
  }

  formatDate(entryDate: Date): string {
    const unformattedDate = entryDate.toString();
    const date = new Date(entryDate);
    let result = '';
    switch (date.getDay()) {
      case 0:
        result += 'Sunday, ';
        break;
      case 1:
        result += 'Monday, ';
        break;
      case 2:
        result += 'Tuesday, ';
        break;
      case 3:
        result += 'Wednesday, ';
        break;
      case 4:
        result += 'Thursday, ';
        break;
      case 5:
        result += 'Friday, ';
        break;
      case 6:
        result += 'Saturday, ';
        break;
    }
    result += unformattedDate.substring(8,10) + '.';
    result += unformattedDate.substring(5,7) + '.';
    result += unformattedDate.substring(0,4);

    return result;
  }

  effectsToString(effects: Effect[]) {
    let resultString = '';
    for (const effect of effects) {
      resultString += `${effect.name} (${effect.intensity}) `;
    }
    return resultString;
  }
  medsToString(medIds: number[]) {
    let resultString = '';
    for (const id of medIds) {
      for (const med of this.medications){
        if (id === med.medId) {
          resultString += med.name + ' (' + med.dosage + ') ';
        }
      }
    }
    return resultString;
  }

  fetchMeds(): void {
    this.medicationService.getForUser().subscribe({
      next: data => {
        this.medications = data;
      }, error: err => {
        console.log(err);
      }
    });
  }

}
