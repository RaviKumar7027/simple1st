// src/components/AddJournal.js
import React, { useState, useEffect } from "react";
import axios from "../api";
import { useNavigate } from "react-router-dom";

function AddJournal() {
  const navigate = useNavigate();
  const [journal, setJournal] = useState({ title: "", content: "", userId: "" });
  const [users, setUsers] = useState([]);

  useEffect(() => {
    // Load users for dropdown
    axios.get("/users").then((res) => setUsers(res.data));
  }, []);

  const onChange = (e) => {
    setJournal({ ...journal, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post("/hello/p", {
        title: journal.title,
        content: journal.content,
        user: { id: journal.userId },
      });
      navigate("/journals");
    } catch (err) {
      console.error("Error adding journal:", err);
    }
  };

  return (
    <div>
      <h2>Add Journal</h2>
      <form onSubmit={onSubmit}>
        <input
          type="text"
          name="title"
          value={journal.title}
          onChange={onChange}
          placeholder="Title"
          required
        />
        <br />
        <textarea
          name="content"
          value={journal.content}
          onChange={onChange}
          placeholder="Content"
          required
        />
        <br />
        <select name="userId" value={journal.userId} onChange={onChange} required>
          <option value="">Select User ID</option>
          {users.map((user) => (
            <option key={user.id} value={user.id}>
              {user.id}
            </option>
          ))}
        </select>

        <br /><br />
        <button type="submit">Add Journal</button>
      </form>
    </div>
  );
}

export default AddJournal;
