import axios from "axios"
const logIn = (username, password) => {
    return axios.post("http://localhost:8080/user/logIn",{ name:username, password:password});
};

export {logIn};
