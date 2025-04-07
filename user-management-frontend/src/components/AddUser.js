// src/components/AddUser.js
import React, { useState } from "react";
import axios from "../api";

function AddUser() {
  const [user, setUser] = useState({
    username: "",
    password: "",
    version: 0,
  });

  const onInputChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post("/users", user);
      alert("User added successfully!");
      setUser({ username: "", password: "", version: 0 });
    } catch (error) {
      console.error("Error adding user:", error);
      alert("Something went wrong!");
    }
  };

  return (
    <div className="container">
      <h2>Add New User</h2>
      <form onSubmit={onSubmit}>
        <div>
          <label>Username: </label>
          <input
            type="text"
            name="username"
            value={user.username}
            onChange={onInputChange}
            required
          />
        </div>
        <br />
        <div>
          <label>Password: </label>
          <input
            type="password"
            name="password"
            value={user.password}
            onChange={onInputChange}
            required
          />
        </div>
        <br />
        <div>
          <label>Version: </label>
          <input
            type="number"
            name="version"
            value={user.version}
            onChange={onInputChange}
          />
        </div>
        <br />
        <button type="submit">Add User</button>
      </form>
    </div>
  );
}

export default AddUser;
