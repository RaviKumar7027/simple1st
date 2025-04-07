// src/components/ViewUser.js
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "../api";

function ViewUser() {
  const { id } = useParams();
  const [user, setUser] = useState(null);

  useEffect(() => {
    loadUser();
  }, []);

  const loadUser = async () => {
    try {
      const res = await axios.get(`/users/${id}`);
      setUser(res.data);
    } catch (err) {
      console.error("Error loading user:", err);
    }
  };

  if (!user) return <div>Loading user data...</div>;

  return (
    <div className="container">
      <h2>User Details</h2>
      <p><strong>ID:</strong> {user.id}</p>
      <p><strong>Username:</strong> {user.username}</p>
      <p><strong>Password:</strong> {user.password}</p>
      <p><strong>Version:</strong> {user.version}</p>
    </div>
  );
}

export default ViewUser;
