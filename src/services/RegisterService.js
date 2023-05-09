import axios from "axios"

const register=(username, password)=>{
    return axios.post("http://localhost:8080/user/register",{name:username, password:password})
}
export {register};
