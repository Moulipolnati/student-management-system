import api from "./api";

const getAllStudents = () => {
    return api.get("/students");
};

const addStudent = (student) => {
    return api.post("/students", student);
};

export default {
    getAllStudents,
    addStudent
};