function Dashboard() {

    const token =
        localStorage.getItem("token");

    return (

        <div className="container mt-5">

            <h2>
                Dashboard
            </h2>

            <p>
                Login Successful
            </p>

            <p>
                JWT Token Stored:
            </p>

            <textarea
                className="form-control"
                rows="6"
                value={token || ""}
                readOnly
            />

        </div>
    );
}

export default Dashboard;