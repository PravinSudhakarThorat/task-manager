import api from "./axios";
export const fetchTasks = () => api.get("/tasks").then(r=>r.data);
export const createTask = (payload:any) => api.post("/tasks", payload).then(r=>r.data);
