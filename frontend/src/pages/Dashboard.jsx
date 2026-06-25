import Navbar from "../components/Navbar";

function Dashboard() {

    const username =
        localStorage.getItem(
            "username"
        );

    const role =
        localStorage.getItem(
            "role"
        );

    return (

        <>

            <Navbar />

            <div className="container mt-4">

                <div className="card shadow">

                    <div className="card-body">

                        <h2 className="mb-3">

                            Student Management System

                        </h2>

                        <h4>

                            Welcome,
                            {" "}
                            {username}

                        </h4>

                        <h5 className="text-primary">

                            Role :
                            {" "}
                            {
                                role === "ROLE_ADMIN"
                                    ? "Administrator"
                                    : "User"
                            }

                        </h5>

                        <hr />

                        {
                            role === "ROLE_ADMIN" ?

                                <>

                                    <h5>
                                        Admin Dashboard
                                    </h5>

                                    <ul>

                                        <li>
                                            Manage Students
                                        </li>

                                        <li>
                                            Manage Courses
                                        </li>

                                        <li>
                                            Manage Enrollments
                                        </li>

                                        <li>
                                            Full System Access
                                        </li>

                                    </ul>

                                </>

                                :

                                <>

                                    <h5>
                                        User Dashboard
                                    </h5>

                                    <ul>

                                        <li>
                                            View Students
                                        </li>

                                        <li>
                                            View Courses
                                        </li>

                                        <li>
                                            View Enrollments
                                        </li>

                                    </ul>

                                </>

                        }

                    </div>

                </div>

            </div>

        </>

    );
}

export default Dashboard;