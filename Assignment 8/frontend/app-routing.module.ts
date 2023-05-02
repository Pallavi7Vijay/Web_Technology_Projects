import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookingComponent } from './booking/booking.component';
import { SearchComponent } from './search/search.component';

const routes: Routes = [{path: '', component : SearchComponent},
{path: 'search', component : SearchComponent},
{path: 'booking', component:BookingComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
