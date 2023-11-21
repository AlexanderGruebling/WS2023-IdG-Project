import { Component, OnInit } from '@angular/core';
import {ProfileService} from '../../services/profile.service';
import {Registration} from '../../dtos/registration';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  profile: Registration = {
    username: '',
    email: '',
    password: ''
  };

  constructor(
    private titleService: Title,
    private service: ProfileService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private modalService: NgbModal,
  ) {
    titleService.setTitle('Profile');
  }

  ngOnInit(): void {
    if (!this.authService.isLoggedIn()) {
      this.router.navigate(['login']);
    } else {
      this.service.getUser().subscribe(data => {
        this.profile.username = data.username;
        this.profile.email = data.email;
      },);
    }
  }

  delete(): void {
    this.service.deleteUser().subscribe({
      next: () => {
        //this.notification.success(`Account ${this.profile.email} successfully deleted`);
        this.router.navigate(['/registration']);
      },
      error: error => {
        console.error('Error deleting account: ', error);
        //this.notification.error(error.error.error, error.error.message);
      },
    });
    this.authService.logoutUser();
  }


  getUsername(): string {
    return this.profile.username;
  }

  getEmail(): string {
    return this.profile.email;
  }

  public openModal(profileDeleteWindow) {
    this.modalService.open(profileDeleteWindow, {backdrop: 'static',size: 'lg'});
  }

}
