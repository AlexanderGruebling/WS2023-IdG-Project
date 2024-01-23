import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Effect} from '../../../dtos/effect';
import {Medication} from '../../../dtos/Medication';
import {EffectService} from "../../../services/effect.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-add-effect',
  templateUrl: './add-effect.component.html',
  styleUrls: ['./add-effect.component.scss']
})
export class AddEffectComponent implements OnInit, OnChanges {
  @Input() currentMed: Medication;
  @Input() resetEffect = false;
  @Output() effectEvent = new EventEmitter<Effect>();
  effect: Effect;
  effectName = '';
  customEffect = '';
  customEffectTriggered = false;
  effectsForUser: string[] = [];

  constructor(
    private effectService: EffectService,
    private toastrService: ToastrService,
  ) {
  }
  ngOnInit() {
    this.getEffectsForUser();
    console.log(this.currentMed);
    this.effect = new Effect(this.effectName, '', 0,false, this.currentMed !== undefined ? this.currentMed.medId : null);
  }
  ngOnChanges(changes: SimpleChanges) {
    if (this.resetEffect) {
      this.effectName = 'None';
      return;
    }
    if (this.effectsForUser.length <= 0) {
      this.effectName = 'None';
    } else {
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
  getEffectsForUser(): void {
    this.effectService.getAllEffectNames().subscribe({
      next: value => {
        if (!value.includes('None')) {
          value.push('None');
        }
        if (!value.includes('Other')) {
          value.push('Other');
        }
        this.effectsForUser = value;
        console.log(this.effectsForUser);
      },
      error: err => this.toastrService.error('Error!', 'Could not fetch your effects.')
    });
  }
}
