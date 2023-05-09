import './App.css';
import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Register from "./components/Register";
import CardComp from "./components/Card";
import {useState} from "react";
import LogIn from "./components/LogIn";
import Home from "./components/Home";
import Books from "./components/Books";

function App() {

    const [setIdPersoana] = useState();
    const setTheId = (id) => {
        setIdPersoana(id);
    };

    return (
    <div className="App">
      <Router>
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/register" element={<Register />} />
            <Route path="/login" element={<LogIn usrLogg={setTheId}/>} />
            <Route path="/admin" element={<Books/>} />
            <Route path="/client" element={<Books/>} />

        </Routes>
      </Router>
    </div>
  );
}

export default App;
