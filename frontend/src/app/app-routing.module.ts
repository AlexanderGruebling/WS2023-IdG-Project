import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {LoginComponent} from './components/login/login.component';
import {AddMedsComponent} from './components/add-meds/add-meds.component';
import {RegistrationComponent} from './components/registration/registration.component';
import {ProfileComponent} from './components/profile/profile.component';
import {AddEntryComponent} from './components/add-entry/add-entry.component';
import {StatsComponent} from './components/stats/stats.component';
import {CalendarComponent} from './components/calendar/calendar.component';
import {EntriesComponent} from './components/entries/entries.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'add-meds', component: AddMedsComponent},
  {path: 'add-entry', component: AddEntryComponent},
  {path: 'stats', component: StatsComponent},
  {path: 'calendar', component: CalendarComponent},
  {path: 'entries', component: EntriesComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
