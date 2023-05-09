import axios  from "axios";

const viewCart = (id) =>{
    return axios.get(`http://localhost:8080/cart/all/${id}` );
};

const emptyCart = (id) =>{
    return axios.delete(`http://localhost:8080/cart/emptyCart/${id}`);
};

const totalCart = (id) =>{
    return axios.get(`http://localhost:8080/cart/totalPrice/${id}`);
};

const orderProducts = (id) =>{
    return axios.post(`http://localhost:8080/cart/addToCart/${id}`);
};
export {viewCart, emptyCart, totalCart, orderProducts};