import { useEffect, useState } from "react";

import Navbar from "../components/Navbar";

import StudentService from "../services/StudentService";

function Students() {

    const [students, setStudents] =
        useState([]);

    useEffect(() => {

        loadStudents();

    }, []);

    const loadStudents = async () => {

        try {

            const response =
                await StudentService
                    .getAllStudents();

            setStudents(response.data);

        } catch (error) {

            console.error(error);
        }
    };

    return (

        <>
            <Navbar />

            <div className="container mt-4">

                <h2>
                    Students
                </h2>

                <table className="table table-bordered">

                    <thead>

                    <tr>

                        <th>ID</th>

                        <th>First Name</th>

                        <th>Last Name</th>

                        <th>Email</th>

                    </tr>

                    </thead>

                    <tbody>

                    {
                        students.map(student => (

                            <tr key={student.id}>

                                <td>{student.id}</td>

                                <td>{student.firstName}</td>

                                <td>{student.lastName}</td>

                                <td>{student.email}</td>

                            </tr>
                        ))
                    }

                    </tbody>

                </table>

            </div>
        </>
    );
}

export default Students;