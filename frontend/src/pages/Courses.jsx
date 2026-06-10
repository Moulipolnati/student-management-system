import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import CourseService from "../services/CourseService";

function Courses() {

    const [courses, setCourses] = useState([]);

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

            await CourseService.addCourse(course);

            setCourse({
                courseName: "",
                description: "",
                duration: "",
                fee: ""
            });

            loadCourses();

        } catch (error) {

            console.error(error);
        }
    };

    return (

        <>
            <Navbar />

            <div className="container mt-4">

                <h2>Add Course</h2>

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

                        Save Course

                    </button>

                </form>

                <hr />

                <h2>Courses List</h2>

                <table className="table table-bordered">

                    <thead>

                    <tr>

                        <th>ID</th>
                        <th>Course Name</th>
                        <th>Description</th>
                        <th>Duration</th>
                        <th>Fee</th>

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

                        </tr>

                    ))}

                    </tbody>

                </table>

            </div>

        </>
    );
}

export default Courses;