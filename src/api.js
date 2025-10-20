import axios from 'axios';

const API_BASE = "https://my-backend.up.railway.app";

// Remove /api from the base URL ↑

export const getUsers = async () => {
  try {
    const url = `${API_BASE}/users/`;  // Now this calls /users/
    console.log('Making API call to:', url);

    const response = await axios.get(url);
    console.log('API Response status:', response.status);
    console.log('API Response data:', response.data);

    return response.data;
  } catch (error) {
    console.error('Error fetching users:', error);
    return [];
  }
};