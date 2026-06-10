import api from "./api";

const getAllStudents = () => {
    return api.get("/students");
};

const addStudent = (student) => {
    return api.post("/students", student);
};

const deleteStudent = (id) => {
    return api.delete(`/students/${id}`);
};

const searchStudents = (keyword) => {
    return api.get(
        `/students/search?keyword=${keyword}`
    );
};
const updateStudent = (id, student) => {
    return api.put(
        `/students/${id}`,
        student
    );
};

export default {
    getAllStudents,
    addStudent,
    deleteStudent,
    searchStudents,
    updateStudent
};