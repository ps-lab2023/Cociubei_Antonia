import React, { useState } from 'react';
import {updatePrice, deleteBook} from "../services/BooksService";

import {
    Card,
    CardActionArea,
    CardContent,
    CardActions,
    Typography,
    Button,
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    TextField,
} from '@mui/material';


function CardComp(props){
    //const rol=JSON.parse(localStorage.getItem("rol")).rol;

    const { rol=JSON.parse(localStorage.getItem("rol")).rol, book } = props;
    const [open, setOpen] = useState(false);
    const [price, setPrice] = useState(book.price.toFixed(2));


    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

   /* const handleDelete = () => {
        deleteBook(book.id)
            .then((response) => {
                if (response.status === 200) {
                    console.log('Book deleted successfully');
                    props.onBookDelete(book.id);
                } else {
                    console.error('Failed to delete the book');
                }
            })
            .catch((error) => {
                console.error('Error deleting the book:', error);
            });
    };*/

    const handleDelete = () => {
        deleteBook(book.id)
            .then(() => {
                console.log('Book deleted successfully');
                props.onBookDelete(book.id);
            })
            .catch((error) => {
                console.error('Error deleting the book:', error);
                if (error.response && error.response.data) {
                    console.error('Error message:', error.response.data);
                }
            });
    };


    const handleSave = async () => {
        try {
            const response = await updatePrice(book.id, price);
            if (response.status === 200) {
                console.log('Price updated successfully');
                const updatedBook = { ...book, price: parseFloat(price) };
                props.onPriceUpdate(updatedBook);

            } else {
                console.error('Failed to update the price');
            }
        } catch (error) {
            console.error('Error updating the price:', error);
        }
        setOpen(false);
    };

    const handlePriceChange = (event) => {
        setPrice(event.target.value);
    };

    const handleButtonClick = () => {
        if (rol === 'ADMIN') {
            handleClickOpen();
        } else {
            props.addToCart(props.book, props.cartItems);
        }
    };


    return (
        <Card sx={{ maxWidth: 345, boxShadow: 3, borderRadius: 2 }}>
            <CardActionArea>
                <CardContent>
                    <Typography gutterBottom variant="h5" component="div" sx={{ marginBottom: 2 }}>
                        {props.book.title}
                    </Typography>
                    <Typography variant="body2" color="text.secondary" sx={{ marginBottom: 4 }}>
                        {props.book.author}
                    </Typography>
                    <Typography variant="body2" color="text.secondary" sx={{ fontWeight: 'bold', marginBottom: 2 }}>
                        <span>{`$${props.book.price.toFixed(2)}`}</span>
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                        {props.book.description}
                    </Typography>
                </CardContent>
            </CardActionArea>
            <CardActions>
                <Button size="small" color="primary" onClick={handleButtonClick}>
                    {rol === 'ADMIN' ? 'MODIFY' : 'ADD TO CART'}
                </Button>
                {rol === 'ADMIN' && (
                    <Button size="small" color="secondary" onClick={handleDelete}>
                        DELETE
                    </Button>
                )}
            </CardActions>

            {rol === 'ADMIN' && (
                <Dialog open={open} onClose={handleClose}>
                    <DialogTitle>Modify Book Price</DialogTitle>
                    <DialogContent>
                        <TextField
                            autoFocus
                            margin="dense"
                            id="price"
                            label="Price"
                            type="number"
                            value={price}
                            onChange={handlePriceChange}
                            fullWidth
                            variant="outlined"
                        />
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleClose}>Cancel</Button>
                        <Button onClick={handleSave} color="primary">
                            Save
                        </Button>
                    </DialogActions>
                </Dialog>
            )}
        </Card>
    );
};
export default CardComp;


/*
    const handlePriceUpdate = (updatedBook) => {
        setBooks((prevBooks) => {
            return prevBooks.map((book) =>
                book.id === updatedBook.id ? updatedBook : book
            );
        });
    };
*/


/*return (
        <Card sx={{ maxWidth: 345 }}>
            <CardActionArea>

                <CardContent>
                    <Typography gutterBottom variant="h5" component="div">
                        {props.book.title}
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                        {props.book.author}
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                        {props.book.description}
                    </Typography>
                </CardContent>
            </CardActionArea>
            <CardActions>
                <Button size="small" color="primary">
                    { rol==="ADMIN" ? "MODIFY" : "ADD TO CART"}
                </Button>
            </CardActions>
        </Card>
    );*/
