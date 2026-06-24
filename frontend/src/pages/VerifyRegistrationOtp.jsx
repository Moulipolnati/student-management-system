import { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthService from "../services/AuthService";

function VerifyRegistrationOtp() {

    const navigate =
        useNavigate();

    const [otp, setOtp] =
        useState("");

    const [message,
        setMessage] =
        useState("");

    const email =
        localStorage.getItem(
            "registrationEmail"
        );

    const handleSubmit =
        async (e) => {

            e.preventDefault();

            try {

                const response =
                    await AuthService
                        .verifyRegistrationOtp(
                            email,
                            otp
                        );

                setMessage(
                    response.data.message
                );

                localStorage.removeItem(
                    "registrationEmail"
                );

                setTimeout(() => {

                    navigate(
                        "/login"
                    );

                }, 2000);

            } catch (error) {

                console.error(
                    error
                );

                setMessage(
                    "OTP Verification Failed"
                );
            }
        };

    return (

        <div className="container mt-5">

            <div className="row justify-content-center">

                <div className="col-md-4">

                    <div className="card">

                        <div className="card-body">

                            <h3 className="text-center">

                                Verify Registration OTP

                            </h3>

                            {message && (

                                <div className="alert alert-info">

                                    {message}

                                </div>
                            )}

                            <form
                                onSubmit={
                                    handleSubmit
                                }>

                                <input
                                    type="text"
                                    className="form-control mb-3"
                                    placeholder="Enter OTP"
                                    value={otp}
                                    onChange={(e) =>
                                        setOtp(
                                            e.target.value
                                        )
                                    }
                                />

                                <button
                                    className="btn btn-success w-100">

                                    Verify OTP

                                </button>

                            </form>

                        </div>

                    </div>

                </div>

            </div>

        </div>
    );
}

export default VerifyRegistrationOtp;