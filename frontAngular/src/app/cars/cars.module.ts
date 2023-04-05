import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../material/material.module';
import { CarsRoutingModule } from './cars-routing.module';
import { HomePageComponent } from './pages/home-page/home-page.component';
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
    ReactiveFormsModule,
    MaterialModule,
    CarsRoutingModule
  ]
})
export class CarsModule { }
