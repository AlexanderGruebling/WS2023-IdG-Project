import {Component, OnInit} from '@angular/core';
import {PlotData} from '../../dtos/plotData';
import {EffectService} from '../../services/effect.service';
import {Chart} from 'chart.js/auto';
import {MedicationService} from '../../services/medication.service';
import {Medication, MedicationWithEntries} from '../../dtos/Medication';
import {ToastrService} from 'ngx-toastr';
import {EffectWithEntries} from '../../dtos/effect';
import {lastValueFrom} from 'rxjs';
import {DosagePlotData} from '../../dtos/dosagePlotData';

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.scss']
})
export class StatsComponent implements OnInit {
  selectedMeds: Set<Medication> = new Set();
  selectedEffects: Set<string> = new Set();
  meds: Medication[];
  effectNames: string[];
  plotData: PlotData[] = [];
  chart: Chart;

  constructor(
    private effectService: EffectService,
    private medicationService: MedicationService,
    private toastrService: ToastrService,
  ) {
  }

  ngOnInit(): void {
    this.getMedicationsForUser();
    this.getAllEffectNames();
    this.chart = this.buildChart();
  }

  getPlotDataForEffect(effect: string) {
    this.effectService.getPlotData(effect).subscribe({
      next: response => this.plotData = response,
      error: err => this.toastrService.error('Error!', 'Please contact our administrator.')
    });
  }

  getMedicationsForUser() {
    this.medicationService.getForUser().subscribe({
      next: response => this.meds = response,
      error: err => this.toastrService.error('Error!', 'Please contact our administrator.')
    });
  }

  getAllEffectNames() {
    this.effectService.getAllEffectNames().subscribe({
      next: response => this.effectNames = response,
      error: err => this.toastrService.error('Error!', 'Please contact our administrator.')
    });
  }

  async getPlotForMedication() {
    const medicationsWithEntries: MedicationWithEntries[] = [];
    for (const medication of this.selectedMeds) {
      await lastValueFrom(this.medicationService.getForName(medication.name)).then(response => {
        medicationsWithEntries.push(new MedicationWithEntries(
          medication,
          response.map(x => new DosagePlotData(new Date(x.date), x.dosage))));
      });
    }
    return medicationsWithEntries;
  }

  async getPlotForEffects() {
    const effectsWithEntries: EffectWithEntries[] = [];
    for (const effect of this.selectedEffects) {
      await lastValueFrom(this.effectService.getPlotData(effect)).then(response => {
        effectsWithEntries.push(new EffectWithEntries(
          effect,
          response.map(x => new PlotData(new Date(x.date), x.intensity))));
      });
    }
    return effectsWithEntries;
  }

  toggleSet(setToToggle: Set<any>, propertyToToggleBy: any) {
    if (setToToggle.has(propertyToToggleBy)) {
      setToToggle.delete(propertyToToggleBy);
    } else {
      setToToggle.add(propertyToToggleBy);
    }
    this.updatePlot();
  }

  updatePlot() {
    this.getPlotForEffects().then((effectEntries: EffectWithEntries[]) => {
      this.getPlotForMedication().then((medicationEntries: MedicationWithEntries[]) => {
        const collectedEntries: (EffectWithEntries | MedicationWithEntries)[] = [...effectEntries, ...medicationEntries];
        console.log(collectedEntries);
        this.updateDatasets(collectedEntries);
      });
    });
  }

  updateDatasets(entries: (MedicationWithEntries | EffectWithEntries)[]) {
    const newDatasets = [];
    const newLabels: Date[] = [];
    for (const entry of entries) {
      if (entry.entriesForPlot.length === 0) {
        continue;
      }
      if (entry instanceof MedicationWithEntries) {
        const dateStringsMap = new Map();
        for (const x of entry.entriesForPlot.sort((a, b) => a.date.getTime() - b.date.getTime())) {
          const date = new Date(x.date);
          dateStringsMap.set(x, date.toDateString());
          newLabels.push(date);
        }
        if (entry.selectedMed.dosage <= 1) {
          entry.entriesForPlot.map(plotEntry => plotEntry.dosage = 500);
        }
        newDatasets.push({
          type: 'bar',
          label: entry.selectedMed.name,
          data: entry.entriesForPlot.map(plotEntry => ({x: dateStringsMap.get(plotEntry), y: plotEntry.dosage}))
        });
      } else {
        const dateStringsMap = new Map();
        for (const x of entry.entriesForPlot.sort((a, b) => a.date.getTime() - b.date.getTime())) {
          const date = new Date(x.date);
          dateStringsMap.set(x, date.toDateString());
          newLabels.push(date);
        }
        newDatasets.push({
          type: 'line',
          label: entry.effectName,
          yAxisID: 'y1',
          data: entry.entriesForPlot.map(plotEntry => ({x: dateStringsMap.get(plotEntry), y: plotEntry.intensity}))
        });
      }
    }
    this.chart.data.datasets = newDatasets;
    this.chart.data.labels = [...new Set(newLabels.sort((a, b) => (a.getTime() - b.getTime()))
      .map(date => date.toDateString()))];
    this.chart.update();
    console.log(this.chart.data.datasets);
  }

  getLast7Days() {
    const today = new Date();
    const dateStrings = [];

    for (let i = 6; i >= 0; i--) {
      const currentDate = new Date(today);
      currentDate.setDate(today.getDate() - i);
      dateStrings.push((currentDate).toDateString());
    }
    return dateStrings;
  }

  buildChart(): Chart {
    return new Chart('MyBarChart', {
      data: {
        datasets: [],
      },
      options: {
        scales: {
          y: {
            min: 0,
            max: 1000,
          },
          y1: {
            display: true,
            min: 0,
            max: 10,
            position: 'right'
          }
        }
      }
    });
  }
}
