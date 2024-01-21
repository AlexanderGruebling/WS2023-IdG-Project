import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './components/header/header.component';
import {FooterComponent} from './components/footer/footer.component';
import {HomeComponent} from './components/home/home.component';
import {LoginComponent} from './components/login/login.component';
import {AddMedsComponent} from './components/add-meds/add-meds.component';
import {AddMedComponent} from './components/add-med/add-med.component';
import {AddEntryComponent} from './components/add-entry/add-entry.component';
import {AddEffectComponent} from './components/add-entry/add-effect/add-effect.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {httpInterceptorProviders} from './interceptors';
import {RegistrationComponent} from './components/registration/registration.component';
import { ProfileComponent } from './components/profile/profile.component';
import {ToastrModule} from 'ngx-toastr';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgxMaskModule} from 'ngx-mask';
import { MainButtonComponent } from './components/home/main-button/main-button.component';
import { StatsComponent } from './components/stats/stats.component';
import { MixedChartComponent } from './components/stats/mixed-chart/mixed-chart.component';
import { BarChartComponent } from './components/stats/bar-chart/bar-chart.component';

import {FullCalendarModule} from '@fullcalendar/angular';
import { CalendarComponent } from './components/calendar/calendar.component';
import { EntryForMedComponent } from './components/add-entry/entry-for-med/entry-for-med.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    LoginComponent,
    RegistrationComponent,
    ProfileComponent,
    AddMedsComponent,
    AddMedComponent,
    MainButtonComponent,
    AddEntryComponent,
    AddEffectComponent,
    StatsComponent,
    MixedChartComponent,
    BarChartComponent,
    CalendarComponent,
    EntryForMedComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
    FormsModule,
    ToastrModule.forRoot(),
    BrowserAnimationsModule,
    NgxMaskModule.forRoot(),
    FullCalendarModule,
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule {
}
