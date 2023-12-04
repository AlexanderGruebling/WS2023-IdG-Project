import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-add-effect',
  templateUrl: './add-effect.component.html',
  styleUrls: ['./add-effect.component.scss']
})
export class AddEffectComponent implements OnInit {
  @Input() effect = '';
  range = 0;

  constructor() {
  }
  ngOnInit() {
  }
}
