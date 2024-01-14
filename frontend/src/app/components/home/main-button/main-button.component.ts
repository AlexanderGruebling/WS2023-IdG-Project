import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-main-button',
  templateUrl: './main-button.component.html',
  styleUrls: ['./main-button.component.scss']
})
export class MainButtonComponent implements OnInit {
  @Input() title = '';
  @Input() refersTo = '';
  @Input() buttonIcon = '';
  text = '';
  asset = '';

  constructor() { }

  ngOnInit(): void {
    switch (this.buttonIcon) {
      case 'calendar-plus':
        this.text = 'Track your prescription intake';
        this.asset = 'pills.jpg';
        break;
      case 'calendar-date':
        this.text = 'View your daily intake';
        this.asset = 'calendar.jpg';
        break;
      case 'bar-chart':
        this.text = 'Gain new insights with charts';
        this.asset = 'chart.jpg';
        break;
      case 'person-circle':
        this.text = 'Manage your profile';
        break;
    }
  }

}
