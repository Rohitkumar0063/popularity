import axios from 'axios';

const API_BASE = import.meta.env.VITE_API_URL; // set in Netlify Environment Variables
axios.get(`${API_BASE}/users`)




export const getUsers = async () => {
  try {
    const url = `${API_BASE}/users`;  // Now this calls /users/
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