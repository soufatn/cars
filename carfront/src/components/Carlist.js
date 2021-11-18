import React, { Component } from 'react';
import {API_URL} from '../constants.js'

class Carlist extends Component {

    constructor(props) {
        super(props);
        this.state = { cars: [] };
    }

    componentDidMount() {
        fetch(API_URL + '/cars')
        .then((response) => response.json())
        .then((responseData) => {
            this.setState({
                cars: responseData,
            });
        })
        .catch(err => console.error(err));
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
                <table>
                    <tbody>{tableRows}</tbody>
                </table>
            </div>
        );
    }
}

export default Carlist;