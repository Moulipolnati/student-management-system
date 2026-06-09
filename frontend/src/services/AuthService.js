import axios from "axios";

const API_URL = "http://localhost:8081/auth";

const login = (loginData) => {
    return axios.post(
        `${API_URL}/login`,
        loginData
    );
};

const register = (registerData) => {
    return axios.post(
        `${API_URL}/register`,
        registerData
    );
};

export default {
    login,
    register
};