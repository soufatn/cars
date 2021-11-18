import React, { Component } from 'react';
import {API_URL} from '../constants.js';
import AddCar from './AddCar';

class Carlist extends Component {

    constructor(props) {
        super(props);
        this.state = { cars: [] };
    }

    componentDidMount() {
        this.fetchCars();
    }

    fetchCars = () => {
        fetch(API_URL + '/cars')
        .then((response) => response.json())
        .then((responseData) => {
            this.setState({
                cars: responseData,
            });
        })
        .catch(err => console.error(err));
    }

    // Add new car
    addCar(car) {
        fetch(API_URL + '/cars', 
        { method: 'POST', 
            headers: {
            'Content-Type': 'application/json',
            },
            body: JSON.stringify(car)
        })
        .then(res => this.fetchCars())
        .catch(err => console.error(err))
    }

    render() {
        const tableRows = this.state.cars.map((car, index) =>
            <tr key={index}>
                <td>{car.name}</td>
                <td>{car.category}</td>
            </tr>
        );
        
        return (
            <div className="App">
                <AddCar addCar={this.addCar} fetchCars={this.fetchCars} />
                <table>
                    <tbody>{tableRows}</tbody>
                </table>
            </div>
        );
    }
}

export default Carlist;