import axios from 'axios';

// HARDCODE the correct URL to bypass all environment variable issues
const API_BASE = 'https://spring-backend-production-5592.up.railway.app';

export const getUsers = async () => {
  try {
    const url = `${API_BASE}/users`;
    console.log('Making API call to:', url);

    const response = await axios.get(url);
    return response.data;
  } catch (error) {
    console.error('Error fetching users:', error);
    return [];
  }
};