import { Link, useNavigate } from "react-router-dom";

function Navbar() {

    const navigate = useNavigate();

    const username =
        localStorage.getItem("username");

    const role =
        localStorage.getItem("role");

    const handleLogout = () => {

        localStorage.removeItem("token");

        localStorage.removeItem("role");

        localStorage.removeItem("username");

        navigate("/login");
    };

    return (

        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">

            <div className="container">

                <Link
                    className="navbar-brand"
                    to="/dashboard">

                    Student Management System

                </Link>

                <div className="navbar-nav">

                    <Link
                        className="nav-link"
                        to="/dashboard">

                        Dashboard

                    </Link>

                    <Link
                        className="nav-link"
                        to="/students">

                        Students

                    </Link>

                    <Link
                        className="nav-link"
                        to="/courses">

                        Courses

                    </Link>

                    <Link
                        className="nav-link"
                        to="/enrollments">

                        Enrollments

                    </Link>

                </div>

                <div className="d-flex align-items-center">

                    <span className="text-white me-3">

                        Welcome,
                        {" "}
                        <strong>
                            {username}
                        </strong>

                        {" | "}

                        {
                            role === "ROLE_ADMIN"
                                ? "Admin"
                                : "User"
                        }

                    </span>

                    <button
                        className="btn btn-danger"
                        onClick={handleLogout}>

                        Logout

                    </button>

                </div>

            </div>

        </nav>
    );
}

export default Navbar;