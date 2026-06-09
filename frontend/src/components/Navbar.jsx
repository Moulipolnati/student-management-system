import { Link, useNavigate } from "react-router-dom";

function Navbar() {

    const navigate = useNavigate();

    const handleLogout = () => {

        localStorage.removeItem("token");

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

                    <button
                        className="btn btn-danger ms-3"
                        onClick={handleLogout}>

                        Logout

                    </button>

                </div>

            </div>

        </nav>
    );
}

export default Navbar;