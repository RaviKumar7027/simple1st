import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';

const EditUser = () => {
  const [user, setUser] = useState({
    username: '',
    password: '',
  });

  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    loadUser();
  }, []);

  const loadUser = async () => {
    try {
      const result = await axios.get(`http://localhost:8080/users/${id}`);
      setUser(result.data);
    } catch (error) {
      console.error('Error loading user:', error);
    }
  };

  const onInputChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`http://localhost:8080/users/${id}`, user);
      alert("User updated successfully!");
      navigate('/');
    } catch (error) {
      alert("Something went wrong!");
    }
  };

  return (
    <div className="container">
      <h2>Edit User</h2>
      <form onSubmit={onSubmit}>
        <div>
          <label>Username:</label>
          <input type="text" name="username" value={user.username} onChange={onInputChange} required />
        </div>
        <div>
          <label>Password:</label>
          <input type="text" name="password" value={user.password} onChange={onInputChange} required />
        </div>
        <button type="submit">Update User</button>
      </form>
    </div>
  );
};

export default EditUser;

