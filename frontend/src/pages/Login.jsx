import { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthService from "../services/AuthService";
import { Link } from "react-router-dom";

function Login() {

    const navigate = useNavigate();

    const [username, setUsername] = useState("");

    const [password, setPassword] = useState("");

    const [error, setError] = useState("");

    const handleSubmit = async (e) => {

    e.preventDefault();

    try {

        const response =
            await AuthService.login({
                username,
                password
            });

        localStorage.setItem(
            "token",
            response.data.token
        );

        localStorage.setItem(
            "role",
            response.data.role
        );

        localStorage.setItem(
            "username",
            username
        );

        navigate("/dashboard");

    } catch (err) {

        setError(
            "Invalid Username or Password"
        );
    }
};

    return (

        <div className="container mt-5">

            <div className="row justify-content-center">

                <div className="col-md-4">

                    <div className="card shadow">

                        <div className="card-body">

                            <h2 className="text-center mb-4">
                                Login
                            </h2>

                            {
                                error &&
                                <div className="alert alert-danger">
                                    {error}
                                </div>
                            }

                            <form onSubmit={handleSubmit}>

                                <div className="mb-3">

                                    <label>
                                        Username
                                    </label>

                                    <input
                                        type="text"
                                        className="form-control"
                                        value={username}
                                        onChange={(e) =>
                                            setUsername(
                                                e.target.value
                                            )}
                                    />
                                </div>

                                <div className="mb-3">

                                    <label>
                                        Password
                                    </label>

                                    <input
                                        type="password"
                                        className="form-control"
                                        value={password}
                                        onChange={(e) =>
                                            setPassword(
                                                e.target.value
                                            )}
                                    />
                                </div>

                                <button
    className="btn btn-primary w-100">

    Login

</button>

<div className="text-center mt-3">

    <Link to="/forgot-password">

        Forgot Password?

    </Link>

</div>

<div className="text-center mt-2">

    <Link to="/register">

        Don't have an account? Register

    </Link>

</div>

                            </form>

                        </div>

                    </div>

                </div>

            </div>

        </div>
    );
}

export default Login;