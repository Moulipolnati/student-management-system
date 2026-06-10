import api from "./api";

const getAllEnrollments = () => {
    return api.get("/enrollments");
};

const createEnrollment = (enrollment) => {
    return api.post("/enrollments", enrollment);
};

const deleteEnrollment = (id) => {
    return api.delete(`/enrollments/${id}`);
};

export default {
    getAllEnrollments,
    createEnrollment,
    deleteEnrollment
};