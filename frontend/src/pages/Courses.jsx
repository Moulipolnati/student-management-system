import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import CourseService from "../services/CourseService";

function Courses() {

    const role =
        localStorage.getItem("role");

    const [courses, setCourses] = useState([]);

    const [editingId, setEditingId] = useState(null);

    const [course, setCourse] = useState({
        courseName: "",
        description: "",
        duration: "",
        fee: ""
    });

    useEffect(() => {
        loadCourses();
    }, []);

    const loadCourses = async () => {

        try {

            const response =
                await CourseService.getAllCourses();

            setCourses(response.data);

        } catch (error) {

            console.error(error);
        }
    };

    const handleChange = (e) => {

        setCourse({
            ...course,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            if (editingId) {

                await CourseService.updateCourse(
                    editingId,
                    course
                );

            } else {

                await CourseService.addCourse(
                    course
                );
            }

            setCourse({
                courseName: "",
                description: "",
                duration: "",
                fee: ""
            });

            setEditingId(null);

            loadCourses();

        } catch (error) {

            console.error(error);
        }
    };

    const handleEdit = (course) => {

        setCourse({
            courseName: course.courseName,
            description: course.description,
            duration: course.duration,
            fee: course.fee
        });

        setEditingId(course.id);
    };

    const handleDelete = async (id) => {

        const confirmDelete =
            window.confirm(
                "Are you sure you want to delete this course?"
            );

        if (!confirmDelete) {
            return;
        }

        try {

            await CourseService.deleteCourse(id);

            loadCourses();

        } catch (error) {

            console.error(error);
        }
    };

    return (

        <>
            <Navbar />

            <div className="container mt-4">

                {role === "ROLE_ADMIN" && (

                    <>

                        <h2>

                            {editingId
                                ? "Update Course"
                                : "Add Course"}

                        </h2>

                        <form onSubmit={handleSubmit}>

                            <div className="row">

                                <div className="col-md-6 mb-3">

                                    <input
                                        type="text"
                                        name="courseName"
                                        placeholder="Course Name"
                                        className="form-control"
                                        value={course.courseName}
                                        onChange={handleChange}
                                        required
                                    />

                                </div>

                                <div className="col-md-6 mb-3">

                                    <input
                                        type="text"
                                        name="duration"
                                        placeholder="Duration"
                                        className="form-control"
                                        value={course.duration}
                                        onChange={handleChange}
                                        required
                                    />

                                </div>

                                <div className="col-md-12 mb-3">

                                    <input
                                        type="text"
                                        name="description"
                                        placeholder="Description"
                                        className="form-control"
                                        value={course.description}
                                        onChange={handleChange}
                                        required
                                    />

                                </div>

                                <div className="col-md-6 mb-3">

                                    <input
                                        type="number"
                                        name="fee"
                                        placeholder="Fee"
                                        className="form-control"
                                        value={course.fee}
                                        onChange={handleChange}
                                        required
                                    />

                                </div>

                            </div>

                            <button
                                type="submit"
                                className="btn btn-primary">

                                {editingId
                                    ? "Update Course"
                                    : "Save Course"}

                            </button>

                        </form>

                        <hr />

                    </>

                )}

                <h2>

                    Courses List

                </h2>

                <table className="table table-bordered">

                    <thead>

                        <tr>

                            <th>ID</th>
                            <th>Course Name</th>
                            <th>Description</th>
                            <th>Duration</th>
                            <th>Fee</th>

                            {role === "ROLE_ADMIN" &&

                                <th>Actions</th>

                            }

                        </tr>

                    </thead>

                    <tbody>

                        {courses.map(course => (

                            <tr key={course.id}>

                                <td>{course.id}</td>
                                <td>{course.courseName}</td>
                                <td>{course.description}</td>
                                <td>{course.duration}</td>
                                <td>{course.fee}</td>

                                {role === "ROLE_ADMIN" && (

                                    <td>

                                        <button
                                            className="btn btn-warning btn-sm me-2"
                                            onClick={() =>
                                                handleEdit(course)
                                            }>

                                            Edit

                                        </button>

                                        <button
                                            className="btn btn-danger btn-sm"
                                            onClick={() =>
                                                handleDelete(course.id)
                                            }>

                                            Delete

                                        </button>

                                    </td>

                                )}

                            </tr>

                        ))}

                    </tbody>

                </table>

            </div>

        </>

    );
}

export default Courses;