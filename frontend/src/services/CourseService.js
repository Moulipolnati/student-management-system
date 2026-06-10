import api from "./api";

const getAllCourses = () => {
    return api.get("/courses");
};

const addCourse = (course) => {
    return api.post("/courses", course);
};

export default {
    getAllCourses,
    addCourse
};