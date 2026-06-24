import api from "./api";

const sendRegistrationOtp = (user) => {
    return api.post(
        "/auth/send-registration-otp",
        user
    );
};

const verifyRegistrationOtp = (
    email,
    otp
) => {
    return api.post(
        "/auth/verify-registration-otp",
        {
            email,
            otp
        }
    );
};

const login = (user) => {
    return api.post(
        "/auth/login",
        user
    );
};

const forgotPassword = (email) => {
    return api.post(
        "/auth/forgot-password",
        {
            email
        }
    );
};

const verifyOtp = (
    email,
    otp
) => {
    return api.post(
        "/auth/verify-otp",
        {
            email,
            otp
        }
    );
};

const resetPassword = (
    email,
    newPassword
) => {
    return api.post(
        "/auth/reset-password",
        {
            email,
            newPassword
        }
    );
};

export default {

    sendRegistrationOtp,

    verifyRegistrationOtp,

    login,

    forgotPassword,

    verifyOtp,

    resetPassword
};