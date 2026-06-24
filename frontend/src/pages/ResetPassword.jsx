import { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthService from "../services/AuthService";

function ResetPassword() {

    const navigate = useNavigate();

    const [newPassword,
        setNewPassword] =
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
                    .resetPassword(
                        email,
                        newPassword
                    );

                localStorage.removeItem(
                    "resetEmail"
                );

                alert(
                    "Password Reset Successful"
                );

                navigate(
                    "/login"
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
                                Reset Password
                            </h3>

                            <form
                                onSubmit={
                                    handleSubmit
                                }>

                                <input
                                    type="password"
                                    className="form-control mb-3"
                                    placeholder="New Password"
                                    value={newPassword}
                                    onChange={(e) =>
                                        setNewPassword(
                                            e.target.value
                                        )}
                                />

                                <button
                                    className="btn btn-success w-100">

                                    Reset Password

                                </button>

                            </form>

                        </div>

                    </div>

                </div>

            </div>

        </div>
    );
}

export default ResetPassword;