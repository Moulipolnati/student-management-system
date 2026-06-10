import api from "./api";

const getAllEnrollments = () => {
    return api.get("/enrollments");
};

const createEnrollment = (enrollment) => {
    return api.post("/enrollments", enrollment);
};

export default {
    getAllEnrollments,
    createEnrollment
};