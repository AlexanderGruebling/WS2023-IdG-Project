import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Effect} from '../../../dtos/effect';
import {Medication} from '../../../dtos/Medication';

@Component({
  selector: 'app-add-effect',
  templateUrl: './add-effect.component.html',
  styleUrls: ['./add-effect.component.scss']
})
export class AddEffectComponent implements OnInit, OnChanges {
  @Input() currentMed: Medication;
  @Input() effectsForUser: string[] = [];
  @Input() resetEffect = false;
  @Output() effectEvent = new EventEmitter<Effect>();
  effect: Effect;
  effectName = '';
  customEffect = '';
  customEffectTriggered = false;

  constructor() {
  }
  ngOnInit() {
    console.log(this.currentMed);
    this.effect = new Effect(this.effectName, '', 0,false, this.currentMed !== undefined ? this.currentMed.medId : null);
  }
  ngOnChanges(changes: SimpleChanges) {
    if (this.resetEffect) {
      this.effectName = 'None';
    }
    if (this.effectsForUser.length <= 0) {
      this.effectName = 'None';
    } else {
      console.log(this.effectsForUser);
      const emptyIndex = this.effectsForUser.indexOf('');
      this.effectsForUser.splice(emptyIndex, 1);
      if (!this.effectsForUser.includes('None')) {
        this.effectsForUser.push('None');
      }
      if (!this.effectsForUser.includes('Other')) {
        this.effectsForUser.push('Other');
      }
      const noneIndex = this.effectsForUser.indexOf('None');
      this.effectName = this.effectsForUser[noneIndex];
    }
  }
  addCustomEffect() {
    this.effectsForUser.push(this.customEffect);
    this.effectName = this.customEffect;
    this.customEffectTriggered = true;
  }
  addNewEffect() {
    this.effect.name = this.effectName;
    this.effect.medId = this.currentMed.medId;
    this.effectEvent.emit(this.effect);
  }
}
