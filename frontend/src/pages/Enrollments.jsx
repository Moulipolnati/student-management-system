import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import EnrollmentService from "../services/EnrollmentService";
import StudentService from "../services/StudentService";
import CourseService from "../services/CourseService";

function Enrollments() {

    const [enrollments, setEnrollments] = useState([]);
    const [students, setStudents] = useState([]);
    const [courses, setCourses] = useState([]);

    const [enrollment, setEnrollment] = useState({
        studentId: "",
        courseId: ""
    });

    useEffect(() => {

        loadEnrollments();
        loadStudents();
        loadCourses();

    }, []);

    const loadEnrollments = async () => {

        try {

            const response =
                await EnrollmentService.getAllEnrollments();

            setEnrollments(response.data);

        } catch (error) {

            console.error(error);
        }
    };

    const loadStudents = async () => {

        try {

            const response =
                await StudentService.getAllStudents();

            setStudents(response.data);

        } catch (error) {

            console.error(error);
        }
    };

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

        setEnrollment({
            ...enrollment,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            await EnrollmentService.createEnrollment(
                enrollment
            );

            setEnrollment({
                studentId: "",
                courseId: ""
            });

            loadEnrollments();

        } catch (error) {

            console.error(error);
        }
    };

    const handleDelete = async (id) => {

        const confirmDelete =
            window.confirm(
                "Are you sure you want to delete this enrollment?"
            );

        if (!confirmDelete) {
            return;
        }

        try {

            await EnrollmentService.deleteEnrollment(id);

            loadEnrollments();

        } catch (error) {

            console.error(error);
        }
    };

    return (

        <>
            <Navbar />

            <div className="container mt-4">

                <h2>Create Enrollment</h2>

                <form onSubmit={handleSubmit}>

                    <div className="row">

                        <div className="col-md-6 mb-3">

                            <select
                                name="studentId"
                                className="form-control"
                                value={enrollment.studentId}
                                onChange={handleChange}
                                required>

                                <option value="">
                                    Select Student
                                </option>

                                {students.map(student => (

                                    <option
                                        key={student.id}
                                        value={student.id}>

                                        {student.firstName} {student.lastName}

                                    </option>

                                ))}

                            </select>

                        </div>

                        <div className="col-md-6 mb-3">

                            <select
                                name="courseId"
                                className="form-control"
                                value={enrollment.courseId}
                                onChange={handleChange}
                                required>

                                <option value="">
                                    Select Course
                                </option>

                                {courses.map(course => (

                                    <option
                                        key={course.id}
                                        value={course.id}>

                                        {course.courseName}

                                    </option>

                                ))}

                            </select>

                        </div>

                    </div>

                    <button
                        type="submit"
                        className="btn btn-primary">

                        Enroll Student

                    </button>

                </form>

                <hr />

                <h2>Enrollments List</h2>

                <table className="table table-bordered">

                    <thead>

                    <tr>

                        <th>ID</th>
                        <th>Student</th>
                        <th>Course</th>
                        <th>Enrollment Date</th>
                        <th>Actions</th>

                    </tr>

                    </thead>

                    <tbody>

                    {enrollments.map(enrollment => (

                        <tr key={enrollment.id}>

                            <td>{enrollment.id}</td>

                            <td>
                                {enrollment.studentName}
                            </td>

                            <td>
                                {enrollment.courseName}
                            </td>

                            <td>
                                {enrollment.enrollmentDate}
                            </td>

                            <td>

                                <button
                                    className="btn btn-danger btn-sm"
                                    onClick={() =>
                                        handleDelete(
                                            enrollment.id
                                        )
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

export default Enrollments;