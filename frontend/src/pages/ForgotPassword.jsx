import { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthService from "../services/AuthService";

function ForgotPassword() {

    const navigate = useNavigate();

    const [email, setEmail] =
        useState("");

    const [message, setMessage] =
        useState("");

    const handleSubmit =
        async (e) => {

            e.preventDefault();

            try {

                await AuthService
                    .forgotPassword(
                        email
                    );

                localStorage.setItem(
                    "resetEmail",
                    email
                );

                setMessage(
                    "OTP Generated Successfully"
                );

                setTimeout(() => {

                    navigate(
                        "/verify-otp"
                    );

                }, 1500);

            } catch (error) {

                console.error(error);
            }
        };

    return (

        <div className="container mt-5">

            <div className="row justify-content-center">

                <div className="col-md-4">

                    <div className="card">

                        <div className="card-body">

                            <h3>
                                Forgot Password
                            </h3>

                            {
                                message &&
                                <div className="alert alert-success">

                                    {message}

                                </div>
                            }

                            <form
                                onSubmit={
                                    handleSubmit
                                }>

                                <input
                                    type="email"
                                    className="form-control mb-3"
                                    placeholder="Enter Email"
                                    value={email}
                                    onChange={(e) =>
                                        setEmail(
                                            e.target.value
                                        )}
                                />

                                <button
                                    className="btn btn-primary w-100">

                                    Generate OTP

                                </button>

                            </form>

                        </div>

                    </div>

                </div>

            </div>

        </div>
    );
}

export default ForgotPassword;