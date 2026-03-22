import axios from "axios";

const BASE_URL = "https://ai-job-portal-backend-i42z.onrender.com"; // replace with your backend URL

const api = axios.create({
  baseURL: BASE_URL,
});

export default api;