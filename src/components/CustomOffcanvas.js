import React, { useEffect } from 'react';
import '../styles/CustomOffcanvas.css';

const CustomOffcanvas = ({
                             show,
                             onHide,
                             cartItems,
                             handleEmptyCart,

                             totalPrice
                         }) => {

    useEffect(() => {
        if (show) {
            const timer = setTimeout(() => {
                onHide();
            }, 5000); // Close after 5 seconds

            return () => clearTimeout(timer);
        }
    }, [show, onHide]);

    return (
        <div className={`custom-offcanvas${show ? ' show' : ''}`} onClick={onHide}>
            <div
                className="custom-offcanvas-content"
                onClick={(e) => e.stopPropagation()}
            >
                <h4>Cart</h4>
                {/* Display cart items */}
                {cartItems.map((item, index) => (
                    <div key={index} className="cart-item">
                        {/* Add your own styles and structure for cart items */}
                        <h5>{item.title}</h5>
                        <p>Author: {item.author}</p>
                        <p>Price: ${item.price}</p>
                        <p>Quantity: {item.quantity}</p>
                    </div>
                ))}
                <button onClick={handleEmptyCart} className="empty-cart-button">
                    Empty Cart
                </button>

                <div className="total-price-display">
                    <p>Total Price: ${totalPrice}</p>
                </div>
            </div>
        </div>
    );
};

export default CustomOffcanvas;



