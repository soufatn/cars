import React, { useState, useEffect } from 'react';
import {API_URL} from '../constants.js';
import AddCar from './AddCar';

const Carlist = (props) => {

    const [cars, setCars] = useState([]);

    const fetchCars = async () => {
      const result = await fetch(API_URL + '/car/');
      const cars = await result.json();
      setCars(cars);
    };

    useEffect(() => {
        fetchCars();
    }, []);

    // Add new car
    const addCar = (car) => {
        fetch(API_URL + '/car/',
        { method: 'POST', 
            headers: {
            'Content-Type': 'application/json',
            },
            body: JSON.stringify(car)
        })
        .then(res => fetchCars())
        .catch(err => console.error(err))
    };

    return (
        <div className="App">
            <AddCar addCar={addCar} fetchCars={fetchCars} />
            <table>
                <tbody>
                {cars.map((car, index) => (
                    <tr key={index}>
                        <td>{car.name}</td>
                        <td>{car.category}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default Carlist;