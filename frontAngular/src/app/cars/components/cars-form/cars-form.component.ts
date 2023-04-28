import { Component } from '@angular/core';
import {FormBuilder, FormGroup, FormControl, Validators} from '@angular/forms';

import { Car } from '../../model/car.model';

@Component({
  selector: 'app-cars-form',
  templateUrl: './cars-form.component.html',
  styleUrls: ['./cars-form.component.scss']
})
export class CarsFormComponent {
  carForm: FormGroup

  constructor(fb: FormBuilder) {
    this.carForm = fb.group({
      name: new FormControl('', {
        validators: [Validators.required],
      }),
      price: new FormControl(0, {
        validators: [Validators.required],
      }),
    });
  }

  createCar(): void {
    const car = this.carForm.getRawValue() as Car
    this.carService.create(car);
  }
}
