import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";

import { HomePageComponent } from './page/home-page/home-page.component';
import { CreatePageComponent } from './pages/create-page/create-page.component';

const routes: Routes = [
    {path: '', redirectTo: 'home', pathMatch: 'full'},
    {path: 'home', component: HomePageComponent},
    {path: 'create', component: CreatePageComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarsRoutingModule { }
