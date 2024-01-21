import { Component, OnInit } from '@angular/core';
import {PlotData} from '../../dtos/plotData';
import {EffectService} from '../../services/effect.service';
import {lastValueFrom} from 'rxjs';
import {Chart} from 'chart.js/auto';
import {MedicationService} from '../../services/medication.service';
import {Medication} from '../../dtos/Medication';

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.scss']
})
export class StatsComponent implements OnInit {
  selectedMeds: Medication[] = [];
  meds: Medication[];
  plotData: PlotData[];
  chart: Chart;

  constructor(
    private effectService: EffectService,
    private medicationService: MedicationService,
  ) {
  }

  async ngOnInit(): Promise<void> {
    this.plotData = await this.getPlotData('Migraine');
    this.meds = await this.getMedicationsForUser();
    this.chart = this.buildChart();
    console.log(this.plotData);
    console.log(this.meds);
  }
  async getPlotData(effect: string): Promise<PlotData[]> {
    return lastValueFrom(this.effectService.getPlotData(effect));
  }
  async getMedicationsForUser(): Promise<Medication[]> {
    return lastValueFrom(this.medicationService.getForUser());
  }
  updateCheckedMeds(medication: Medication) {
    const index = this.selectedMeds.indexOf(medication);
    if (index > -1) {
      this.selectedMeds.splice(index, 1);
    } else {
      this.selectedMeds.push(medication);
    }
  }
  buildChart(): Chart {
    const constructedLabels = [];
    const constructedData = [];
    for (const plot of this.plotData) {
      constructedLabels.push(new Date(plot.date).toDateString());
      constructedData.push(plot.intensity);
    }
    return new Chart('MyBarChart', {
      data: {
        labels: constructedLabels,
        datasets: [{
          type: 'line',
          label: 'Migraine Intensity',
          data: constructedData,
        }]
      },
      options: {
        scales: {
          y: {
            min: 0,
            max: 10,
            position: 'right'
          },
          y1: {
            type: 'linear',
            display: true,
          }
        }
      }
    });
  }
}
