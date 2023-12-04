import {Component, Input, OnInit} from '@angular/core';
import {Chart} from 'chart.js/auto';

@Component({
  selector: 'app-mixed-chart',
  templateUrl: './mixed-chart.component.html',
  styleUrls: ['./mixed-chart.component.scss']
})
export class MixedChartComponent implements OnInit {
  @Input() title = '';
  public chart: any;

  constructor() { }

  ngOnInit(): void {
    this.createChart();
  }

  createChart(): void {
    this.chart = new Chart('MyChart', {
      data: {// values on X-Axis
        labels: ['2023-11-29', '2023-11-30', '2023-12-01', '2023-12-02',
          '2023-12-03', '2023-12-04', '2023-12-04', '2023-12-05',],
        datasets: [
          {
            type: 'line',
            label: 'Migraine Intensity',
            data: ['312', '470', '550', '590', '600',
              '370', '100', '0'],
            backgroundColor: 'blue'
          },
          {
            type: 'bar',
            label: 'Aspirin Dosage',
            data: ['0', '0', '500', '500', '250',
              '250', '0', '0'],
            backgroundColor: 'limegreen'
          }
        ]
      },
      options: {
        aspectRatio: 2.5
      }
    });
  }

}
