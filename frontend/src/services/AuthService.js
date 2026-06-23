import api from "./api";

const register = (user) => {
    return api.post("/auth/register", user);
};

const login = (user) => {
    return api.post("/auth/login", user);
};

export default {
    register,
    login
};
