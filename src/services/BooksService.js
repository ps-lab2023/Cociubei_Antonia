
import axios from 'axios';

const getBooks = () =>{
    return axios.get("http://localhost:8080/book/listAllBooks");
};

const getBooksByCategory = (category) => {
    return axios.get(`http://localhost:8080/book/category/${category}`);
};

const getBookByTitle = (title) => {
        return axios.get(`http://localhost:8080/book/title/${title}`);
};

const getBookByAuthor = (author) => {
    return axios.get(`http://localhost:8080/book/author/${author}`);
};

const updatePrice = (id, newPrice) => {
    return axios.put(`http://localhost:8080/book/update/${id}`,{ price: newPrice });
};

const deleteBook = (bookId) => {
    return axios.delete(`http://localhost:8080/book/remove/${bookId})`)};

export {deleteBook, getBooks, getBooksByCategory, getBookByTitle, getBookByAuthor, updatePrice}