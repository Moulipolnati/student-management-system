import { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthService from "../services/AuthService";

function VerifyOtp() {

    const navigate = useNavigate();

    const [otp, setOtp] =
        useState("");

    const email =
        localStorage.getItem(
            "resetEmail"
        );

    const handleSubmit =
        async (e) => {

            e.preventDefault();

            try {

                await AuthService
                    .verifyOtp(
                        email,
                        otp
                    );

                navigate(
                    "/reset-password"
                );

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
                                Verify OTP
                            </h3>

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
                                        )}
                                />

                                <button
                                    className="btn btn-primary w-100">

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

export default VerifyOtp;