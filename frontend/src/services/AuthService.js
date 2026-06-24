import api from "./api";

const register = (user) => {
    return api.post("/auth/register", user);
};

const login = (user) => {
    return api.post("/auth/login", user);
};

const forgotPassword = (email) => {
    return api.post("/auth/forgot-password", {
        email
    });
};

const verifyOtp = (email, otp) => {
    return api.post("/auth/verify-otp", {
        email,
        otp
    });
};

const resetPassword = (
    email,
    newPassword
) => {
    return api.post("/auth/reset-password", {
        email,
        newPassword
    });
};

export default {
    register,
    login,
    forgotPassword,
    verifyOtp,
    resetPassword
};