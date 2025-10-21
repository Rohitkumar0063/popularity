import axios from 'axios';


const API_BASE = process.env.REACT_APP_API_URL || 'https://spring-backend-production-5592.up.railway.app';

export const getUsers = async () => {
  try {
    const url = `${API_BASE}/users`;
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