import React, { useState } from 'react';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';

const AddCar = (props) => {
    const [open, setOpen] = useState(false);
    const [car, setCar] = useState({
        name: '', price: ''
    });

    // Open the modal form
    const handleClickOpen = () => {
        setOpen(true);
    };
    
    // Close the modal form
    const handleClose = () => {
        setOpen(false);
    };
    
    const handleChange = (event) => {
        setCar({...car, [event.target.name]: event.target.value});
    }

    // Save car and close modal form
    const handleSave = () => {
        props.addCar(car);
        handleClose();
    }
    
    return (
        <div>
            <button style={{margin: 10}} onClick={handleClickOpen}>New Car</button>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>New car</DialogTitle>
                <DialogContent>
                    <input type="text" placeholder="Name" name="name" value={car.name} onChange={handleChange}/><br/>
                    <input type="text" placeholder="Price" name="price" value={car.price} onChange={handleChange}/><br/>
                </DialogContent>
                <DialogActions>
                    <button onClick={handleClose}>Cancel</button>
                    <button onClick={handleSave}>Save</button>
                </DialogActions>
            </Dialog>
        </div>
    );
};
    
export default AddCar;