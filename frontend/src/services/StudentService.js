import api from "./api";

const getAllStudents = () => {

    return api.get("/students");
};

export default {
    getAllStudents
};