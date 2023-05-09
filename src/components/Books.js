import React from 'react';
import ReactDOM from 'react-dom';
import { useEffect, useState } from 'react'
import { Offcanvas, Button } from "react-bootstrap";
import { Link } from 'react-router-dom';

import "../styles/Books.css"
import CardComp from './Card';
import {deleteBook, getBookByTitle, getBooks, getBooksByCategory, getBookByAuthor} from "../services/BooksService";
import  "../styles/CustomOffcanvas.css"
import CustomOffcanvas from "./CustomOffcanvas";
import {orderProducts} from "../services/CartService";
import {
    viewCart,
    emptyCart,
    totalCart,
} from "../services/CartService";
function BookList() {
    const [books, setBooks] = useState([]);
    const [title, setTitle] = useState("");
    const [author, setAuthor]= useState("")
    const [price, setPrice]= useState("")
    const [selectedCategory, setSelectedCategory] = useState("");
    const categories = [
        { name: "All Categories", value: "" },
        { name: "Fiction", value: "FICTION" },
        { name: "Psychology", value: "PSYCHOLOGY" },
        { name: "Romance", value: "ROMANCE" },
        { name: "Biography", value: "BIOGRAPHY" },
        { name: "Religion", value: "RELIGION" },
    ];
    const [cartItems, setCartItems] = useState([]);
    const [showOffCanvas, setShowOffCanvas] = useState(false);
    const userId = JSON.parse(localStorage.getItem("userId"));

    const [totalPrice, setTotalPrice] = useState(0);


    useEffect(() => {
        getBooks().then((response) => {
            setBooks(response.data);
            console.log(response.data);
        });
    }, []);

    useEffect(() => {
        const fetchUserCart = async () => {
            try {
                const response = await viewCart(userId);
                if (response.status === 200) {
                    setCartItems(response.data);
                } else {
                    console.error('Failed to fetch the cart items');
                }
            } catch (error) {
                console.error('Error fetching the cart items:', error);
            }
        };

        fetchUserCart();
    }, [userId]);


    useEffect(() => {
        const calculateTotalPrice = () => {
            const price = cartItems.reduce((total, item) => total + item.price, 0);
            setTotalPrice(price);
        };

        calculateTotalPrice();
    }, [cartItems]);
    const handleSearchTitle = () => {
        getBookByTitle(title).then((res) => {
            setBooks(res.data);
        });
    };
    const handleSearchAuthor = () => {
        getBookByAuthor(author).then((res) => {
            setBooks(res.data);
        });
    };
    const handleCategorySelect = (event) => {
        setSelectedCategory(event.target.value);
    };

    const filteredBooks = books.filter(
        (book) => selectedCategory === "" || book.category === selectedCategory
    );
    const handleClearSearch = () => {
        setAuthor("");
        setSelectedCategory("");
        getBooks().then((response) => {
            setBooks(response.data);
        });
    };

    const handlePriceUpdate = (updatedBook) => {
        setBooks((prevBooks) => {
            return prevBooks.map((book) =>
                book.id === updatedBook.id ? updatedBook : book
            );
        });
    };
    const handleTotalCart = async () => {
        try {
            const response = await totalCart(userId);
            if (response.status === 200) {
                console.log('Total cart price:', response.data);
                alert(`Total cart price: $${response.data}`);
            } else {
                console.error('Failed to fetch the total cart price');
            }
        } catch (error) {
            console.error('Error fetching the total cart price:', error);
        }
    };

    const addToCart = (book) => {
        setCartItems((prevItems) => {
            const existingItem = prevItems.find((item) => item.id === book.id);
            if (existingItem) {
                return prevItems.map((item) =>
                    item.id === book.id
                        ? {
                            ...item,
                            quantity: item.quantity + 1,
                            price: (item.quantity+1) * item.price,
                        }
                        : item
                );
            }
            return [
                ...prevItems,
                { ...book, quantity: 1, price: book.price*1 },
            ];
        });
        setShowOffCanvas(true);
    };


    const handleViewCartClick = () => {
        setShowOffCanvas(true);
    };

    const handleCloseOffCanvas = () => {
        setShowOffCanvas(false);
    };
    /*const handleEmptyCart = async () => {
        try {
            const response = await emptyCart(userId);
            if (response.status === 200) {
                console.log('Cart emptied successfully');
                setCartItems([]);
            } else {
                console.error('Failed to empty the cart');
            }
        } catch (error) {
            console.error('Error emptying the cart:', error);
        }
    };
*/

    const handleEmptyCart = async () => {
        if (!userId) {
            console.error("User ID is not available");
            return;
        }

        try {
            const response = await emptyCart(userId);
            if (response.status === 200) {
                console.log('Cart emptied successfully');
                setCartItems([]);
                setTotalPrice(0);
            } else {
                console.error('Failed to empty the cart');
            }
        } catch (error) {
            console.error('Error emptying the cart:', error);
        }
    };

    const handleBookDelete = (deletedBookId) => {
        setBooks((prevBooks) => {
            return prevBooks.filter((book) => book.id !== deletedBookId);
        });
    };



    return (
        <div className="book-list-container">
            <button onClick={handleViewCartClick} className="cart-button">
                View Cart
            </button>
            <div className="search-container">
                <input
                    type="text"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    placeholder="title..."
                    className="search-input"
                />
                <button onClick={handleSearchTitle} className="search-button">
                    SEARCH
                </button>
            </div>
            <div className="search-container">
                <input
                    type="text"
                    value={author}
                    onChange={(e) => setAuthor(e.target.value)}
                    placeholder="author..."
                    className="search-input"
                />
                <button onClick={handleSearchAuthor} className="search-button">
                    SEARCH
                </button>
            </div>
            <button onClick={handleClearSearch} className="search-button">
                CLEAR SEARCH
            </button>
            <div className="filter-container">
                <select
                    value={selectedCategory}
                    onChange={handleCategorySelect}
                    className="filter-select"
                >
                    {categories.map((category) => (
                        <option key={category.value} value={category.value}>
                            {category.name}
                        </option>
                    ))}
                </select>
            </div>
            <div className="book-container">
                {filteredBooks.map((book) => (
                    <CardComp
                        key={book.id}
                        book={book}
                        onPriceUpdate={handlePriceUpdate}
                        addToCart={addToCart}
                        cartItems={cartItems}
                    />
                ))}
            </div>

            <CustomOffcanvas show={showOffCanvas}
                             onHide={handleCloseOffCanvas}
                             cartItems={cartItems}
                             handleEmptyCart={handleEmptyCart}
                             handleTotalCart={handleTotalCart}
                             totalPrice={totalPrice}
                             onBookDelete={handleBookDelete} />


        </div>
    );
}

export default BookList;

//continuare compofff
{/*   <h4>Cart</h4>
                 Display cart items
                {cartItems.map((item, index) => (
                    <div key={index} className="cart-item">
                         Add your own styles and structure for cart items
                        <h5>{item.title}</h5>
                        <p>Author: {item.author}</p>
                        <p>Price: ${item.price}</p>
                        <p>Quantity: {item.quantity}</p>
                    </div>
                ))}
            </CustomOffcanvas>*/}



/*return (
      <div>
          <input
              type="text"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              placeholder="title..."
          />
          <button onClick={handleSearchTitle}> SEARCH </button>

          <select value={selectedCategory} onChange={handleCategorySelect}>
              {categories.map((category) => (
                  <option key={category.value} value={category.value}>
                      {category.name}
                  </option>
              ))}
          </select>

          <div className={"afisare"}>
              {filteredBooks.map((book) => (
                  <CardComp key={book.id} book={book} />
              ))}
          </div>
      </div>
  );*/

