import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Effect} from '../../../dtos/effect';
import {Medication} from '../../../dtos/Medication';

@Component({
  selector: 'app-add-effect',
  templateUrl: './add-effect.component.html',
  styleUrls: ['./add-effect.component.scss']
})
export class AddEffectComponent implements OnInit {
  @Input() effectName = '';
  @Input() currentMed: Medication;
  @Output() effectEvent = new EventEmitter<Effect>();
  effect: Effect;

  constructor() {
  }
  ngOnInit() {
    this.effect = new Effect(this.effectName, '', 0,false, this.currentMed.medId);
  }
  addNewEffect() {
    this.effectEvent.emit(this.effect);
  }
}
