// src/components/SearchUser.js
import React, { useState } from "react";
import axios from "../api";

function SearchUser() {
  const [id, setId] = useState("");
  const [user, setUser] = useState(null);
  const [error, setError] = useState("");

  const handleSearch = async () => {
    try {
      const res = await axios.get(`/users/${id}`);
      setUser(res.data);
      setError("");
    } catch (err) {
      setUser(null);
      setError("User not found");
    }
  };

  return (
    <div>
      <h3>Search User by ID</h3>
      <input
        type="text"
        placeholder="Enter User ID"
        value={id}
        onChange={(e) => setId(e.target.value)}
      />
      <button onClick={handleSearch}>Search</button>

      {error && <p>{error}</p>}

      {user && (
        <div>
          <p><strong>ID:</strong> {user.id}</p>
          <p><strong>Username:</strong> {user.username}</p>
          <p><strong>Password:</strong> {user.password}</p>
          <p><strong>Version:</strong> {user.version}</p>
        </div>
      )}
    </div>
  );
}

export default SearchUser;
