import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {SVGService} from '../../services/SVGService';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  profileIcon = '';
  statsIcon = '';

  constructor(
    public authService: AuthService,
    private svgService: SVGService,
  ) { }

  ngOnInit() {
    this.profileIcon = this.svgService.getIcon('btn-profile');
    this.statsIcon = this.svgService.getIcon('btn-stats');
  }

}
