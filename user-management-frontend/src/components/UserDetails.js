// src/components/UserDetails.js
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "../api";

function UserDetails() {
  const { id } = useParams(); // userId from URL
  const [user, setUser] = useState(null);
  const [journals, setJournals] = useState([]);

  useEffect(() => {
    // Load user info
    axios.get(`/users/${id}`)
      .then((res) => setUser(res.data))
      .catch((err) => console.error("User not found", err));

    // Load journals by user ID
    axios.get(`/hello/journals/user/${id}`)
      .then((res) => setJournals(res.data))
      .catch((err) => {
        console.error("Error fetching journals", err);
        setJournals([]); // handle no journals
      });
  }, [id]);

  if (!user) {
    return <div>Loading user details...</div>;
  }

  return (
    <div>
      <h2>User Details</h2>
      <p><strong>ID:</strong> {user.id}</p>
      <p><strong>Username:</strong> {user.username}</p>

      <h3>Journal Entries</h3>
      {journals.length === 0 ? (
        <p>No journal entries found for this user.</p>
      ) : (
        <table border="1" cellPadding="10">
          <thead>
            <tr>
              <th>ID</th>
              <th>Title</th>
              <th>Content</th>
            </tr>
          </thead>
          <tbody>
            {journals.map((j) => (
              <tr key={j.id}>
                <td>{j.id}</td>
                <td>{j.title}</td>
                <td>{j.content}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default UserDetails;
