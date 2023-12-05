import {Component, Input, OnInit} from '@angular/core';
import {Chart} from 'chart.js/auto';

@Component({
  selector: 'app-bar-chart',
  templateUrl: './bar-chart.component.html',
  styleUrls: ['./bar-chart.component.scss']
})
export class BarChartComponent implements OnInit {
  @Input() title = '';
  public chart: any;

  constructor() { }

  ngOnInit(): void {
    this.createChart();
  }
  createChart(): void {
    this.chart = new Chart('MyBarChart', {
      data: {// values on X-Axis
        labels: ['2023-11-29', '2023-11-30', '2023-12-01', '2023-12-02',
          '2023-12-03', '2023-12-04', '2023-12-04', '2023-12-05',],
        datasets: [
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
