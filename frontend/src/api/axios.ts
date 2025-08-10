import axios from "axios";
const API_BASE = import.meta.env.VITE_API_BASE || "http://localhost:8080/api";
const instance = axios.create({ baseURL: API_BASE });
instance.interceptors.request.use(cfg => {
  const token = localStorage.getItem("token");
  if(token) cfg.headers = {...cfg.headers, Authorization: `Bearer ${token}`};
  return cfg;
});
export default instance;
