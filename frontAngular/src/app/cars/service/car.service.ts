import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class CarService {
  protected resourceUrl = 'api/cars';

  constructor(protected http: HttpClient) {}

  create(car: Car): Observable<EntityResponseType> {
    return this.http.post<Car>(this.resourceUrl, car, { observe: 'response' });
  }
}
