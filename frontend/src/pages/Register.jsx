import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import AuthService from "../services/AuthService";

function Register() {

    const navigate = useNavigate();

    const [user, setUser] = useState({
        username: "",
        email: "",
        password: ""
    });

    const [message, setMessage] = useState("");

    const handleChange = (e) => {

        setUser({
            ...user,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            const response =
                await AuthService
                    .sendRegistrationOtp(
                        user
                    );

            localStorage.setItem(
                "registrationEmail",
                user.email
            );

            setMessage(
                response.data.message
            );

            setTimeout(() => {

                navigate(
                    "/verify-registration-otp"
                );

            }, 1500);

        } catch (error) {

            console.error(error);

            if (
                error.response &&
                error.response.data &&
                error.response.data.message
            ) {

                setMessage(
                    error.response.data.message
                );

            } else {

                setMessage(
                    "Failed to send registration OTP"
                );
            }
        }
    };

    return (

        <div className="container mt-5">

            <div className="row justify-content-center">

                <div className="col-md-6">

                    <div className="card">

                        <div className="card-body">

                            <h2 className="text-center mb-4">
                                Register
                            </h2>

                            {
                                message &&

                                <div className="alert alert-info">

                                    {message}

                                </div>
                            }

                            <form
                                onSubmit={
                                    handleSubmit
                                }>

                                <div className="mb-3">

                                    <label>
                                        Username
                                    </label>

                                    <input
                                        type="text"
                                        name="username"
                                        className="form-control"
                                        value={user.username}
                                        onChange={handleChange}
                                        required
                                    />

                                </div>

                                <div className="mb-3">

                                    <label>
                                        Email
                                    </label>

                                    <input
                                        type="email"
                                        name="email"
                                        className="form-control"
                                        value={user.email}
                                        onChange={handleChange}
                                        required
                                    />

                                </div>

                                <div className="mb-3">

                                    <label>
                                        Password
                                    </label>

                                    <input
                                        type="password"
                                        name="password"
                                        className="form-control"
                                        value={user.password}
                                        onChange={handleChange}
                                        required
                                    />

                                </div>

                                <button
                                    type="submit"
                                    className="btn btn-primary w-100">

                                    Send OTP

                                </button>

                            </form>

                            <div className="text-center mt-3">

                                Already have an account?

                                <Link to="/login">

                                    Login

                                </Link>

                            </div>

                        </div>

                    </div>

                </div>

            </div>

        </div>
    );
}

export default Register;