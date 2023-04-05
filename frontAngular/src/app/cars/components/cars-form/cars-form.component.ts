import { Component } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-cars-form',
  templateUrl: './cars-form.component.html',
  styleUrls: ['./cars-form.component.scss']
})
export class CarsFormComponent {
  carForm: FormGroup

  constructor(fb: FormBuilder) {
    this.carForm = fb.group({
    });
  }

  createCar() {

  }
}
