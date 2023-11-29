import {Component, Input, OnInit} from '@angular/core';
import {SVGService} from '../../../services/SVGService';

@Component({
  selector: 'app-main-button',
  templateUrl: './main-button.component.html',
  styleUrls: ['./main-button.component.scss']
})
export class MainButtonComponent implements OnInit {
  @Input() title = '';
  @Input() refersTo = '';
  @Input() buttonIcon = '';
  public icon = '';

  constructor(public svgService: SVGService) { }

  ngOnInit(): void {
    this.icon = this.svgService.getIcon(this.buttonIcon);
  }

}
