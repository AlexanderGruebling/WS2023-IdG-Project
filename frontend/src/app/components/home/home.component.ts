import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Registration} from "../../dtos/registration";
import {ProfileService} from "../../services/profile.service";
import {el} from "@fullcalendar/core/internal-common";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  profile: Registration = {
    username: '',
    email: '',
    password: ''
  };

  constructor(public authService: AuthService,
              public profileService: ProfileService) { }

  ngOnInit() {
    if (this.authService.isLoggedIn()){
      this.profileService.getUser().subscribe(data => {
        this.profile.username = data.username;
      },);
    }
  }

}
