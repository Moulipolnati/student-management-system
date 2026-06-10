import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import StudentService from "../services/StudentService";

function Students() {

    const [students, setStudents] = useState([]);

    const [keyword, setKeyword] = useState("");

    const [editingId, setEditingId] = useState(null);

    const [student, setStudent] = useState({
        firstName: "",
        lastName: "",
        email: "",
        phone: "",
        address: ""
    });

    useEffect(() => {
        loadStudents();
    }, []);

    const loadStudents = async () => {

        try {

            const response =
                await StudentService.getAllStudents();

            setStudents(response.data);

        } catch (error) {

            console.error(error);
        }
    };

    const handleSearch = async () => {

        try {

            if (keyword.trim() === "") {

                loadStudents();
                return;
            }

            const response =
                await StudentService.searchStudents(keyword);

            setStudents(response.data);

        } catch (error) {

            console.error(error);
        }
    };

    const handleChange = (e) => {

        setStudent({
            ...student,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            if (editingId) {

                await StudentService.updateStudent(
                    editingId,
                    student
                );

            } else {

                await StudentService.addStudent(
                    student
                );
            }

            setStudent({
                firstName: "",
                lastName: "",
                email: "",
                phone: "",
                address: ""
            });

            setEditingId(null);

            loadStudents();

        } catch (error) {

            console.error(error);
        }
    };

    const handleEdit = (student) => {

        setStudent({
            firstName: student.firstName,
            lastName: student.lastName,
            email: student.email,
            phone: student.phone,
            address: student.address
        });

        setEditingId(student.id);
    };

    const handleDelete = async (id) => {

        const confirmDelete =
            window.confirm(
                "Are you sure you want to delete this student?"
            );

        if (!confirmDelete) {
            return;
        }

        try {

            await StudentService.deleteStudent(id);

            loadStudents();

        } catch (error) {

            console.error(error);
        }
    };

    return (
        <>
            <Navbar />

            <div className="container mt-4">

                <div className="row mb-4">

                    <div className="col-md-8">

                        <input
                            type="text"
                            className="form-control"
                            placeholder="Search Student..."
                            value={keyword}
                            onChange={(e) =>
                                setKeyword(e.target.value)}
                        />

                    </div>

                    <div className="col-md-4">

                        <button
                            className="btn btn-success w-100"
                            onClick={handleSearch}>

                            Search

                        </button>

                    </div>

                </div>

                <h2>
                    {editingId
                        ? "Update Student"
                        : "Add Student"}
                </h2>

                <form onSubmit={handleSubmit}>

                    <div className="row">

                        <div className="col-md-6 mb-3">
                            <input
                                type="text"
                                name="firstName"
                                placeholder="First Name"
                                className="form-control"
                                value={student.firstName}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="col-md-6 mb-3">
                            <input
                                type="text"
                                name="lastName"
                                placeholder="Last Name"
                                className="form-control"
                                value={student.lastName}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="col-md-6 mb-3">
                            <input
                                type="email"
                                name="email"
                                placeholder="Email"
                                className="form-control"
                                value={student.email}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="col-md-6 mb-3">
                            <input
                                type="text"
                                name="phone"
                                placeholder="Phone"
                                className="form-control"
                                value={student.phone}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="col-md-12 mb-3">
                            <input
                                type="text"
                                name="address"
                                placeholder="Address"
                                className="form-control"
                                value={student.address}
                                onChange={handleChange}
                                required
                            />
                        </div>

                    </div>

                    <button
                        type="submit"
                        className="btn btn-primary">

                        {editingId
                            ? "Update Student"
                            : "Save Student"}

                    </button>

                </form>

                <hr />

                <h2>Students List</h2>

                <table className="table table-bordered">

                    <thead>

                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Actions</th>
                    </tr>

                    </thead>

                    <tbody>

                    {students.map(student => (

                        <tr key={student.id}>

                            <td>{student.id}</td>
                            <td>{student.firstName}</td>
                            <td>{student.lastName}</td>
                            <td>{student.email}</td>

                            <td>

                                <button
                                    className="btn btn-warning btn-sm me-2"
                                    onClick={() =>
                                        handleEdit(student)
                                    }>

                                    Edit

                                </button>

                                <button
                                    className="btn btn-danger btn-sm"
                                    onClick={() =>
                                        handleDelete(student.id)
                                    }>

                                    Delete

                                </button>

                            </td>

                        </tr>

                    ))}

                    </tbody>

                </table>

            </div>
        </>
    );
}

export default Students;