import api from "./api";

const getAllCourses = () => {
    return api.get("/courses");
};

const addCourse = (course) => {
    return api.post("/courses", course);
};
const deleteCourse = (id) => {
    return api.delete(`/courses/${id}`);
};

export default {
    getAllCourses,
    addCourse,
    deleteCourse
};