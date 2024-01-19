import { Component, OnInit } from '@angular/core';
import { CalendarOptions } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import bootstrap5Plugin from '@fullcalendar/bootstrap5';
import {EntryService} from '../../services/entry.service';
import {MedicationService} from '../../services/medication.service';
import {lastValueFrom} from 'rxjs';
import {Entry} from '../../dtos/entry';
import {Medication} from '../../dtos/Medication';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.scss']
})
export class CalendarComponent implements OnInit {
  entriesWithMeds: Map<Entry, Medication[]> = new Map();
  events = [];
  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    plugins: [dayGridPlugin, bootstrap5Plugin],
    themeSystem: 'bootstrap5',
    contentHeight: 600,
    events: []
  };

  constructor(
    private entryService: EntryService,
    private medicationService: MedicationService,
  ) { }

  async ngOnInit(): Promise<void> {
    await this.getForUser();
    this.createEvents();
  }
  async getForUser() {
    const entries = await lastValueFrom(this.entryService.getForUser());
    for (const entry of entries) {
      const medications = [];
      for (const medId of entry.medIds) {
        medications.push(lastValueFrom(this.medicationService.getById(medId)));
      }
      this.entriesWithMeds.set(entry, await Promise.all(medications));
    }
  }
  createEvents() {
    for (const [entry, medications] of this.entriesWithMeds) {
      for (const medication of medications) {
        this.events.push({title: medication.name, date: entry.date});
      }
    }
    this.calendarOptions.events = this.events;
  }

}
