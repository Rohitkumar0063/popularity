import axios from 'axios';

// ✅ Use import.meta.env for Vite
const API_BASE = import.meta.env.VITE_API_URL;
console.log("API_BASE:", API_BASE); // should print: http://localhost:8080/api

export const getUsers = async () => {
  try {
    const response = await axios.get(`${API_BASE}/users`);
    return response.data;
  } catch (error) {
    console.error('Error fetching users:', error);
    return [];
  }
};
