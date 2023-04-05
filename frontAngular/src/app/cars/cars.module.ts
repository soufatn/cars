import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CarsRoutingModule } from '../cars-routing.module';
import { HomePageComponent } from './page/home-page/home-page.component';
import { CreatePageComponent } from './pages/create-page/create-page.component';
import { CarsFormComponent } from './components/cars-form/cars-form.component';

@NgModule({
  declarations: [
    HomePageComponent,
    CreatePageComponent,
    CarsFormComponent
  ],
  imports: [
    CommonModule,
    CarsRoutingModule
  ]
})
export class CarsModule { }
