import React, { useEffect, useState } from "react";
import axios from "../api";
import { useParams, useNavigate } from "react-router-dom";

function EditJournal() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [journal, setJournal] = useState({ title: "", content: "", user: { id: "" } });

  useEffect(() => {
    axios.get(`/hello/${id}`)
      .then((res) => setJournal(res.data))
      .catch((err) => console.error("Failed to fetch journal:", err));
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setJournal({ ...journal, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.put(`/hello/update/${id}`, {
      ...journal,
      user: { id: journal.user.id } // important!
    })
      .then(() => navigate("/journals"))
      .catch((err) => console.error("Failed to update journal:", err));
  };

  return (
    <div>
      <h2>Edit Journal</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="title"
          value={journal.title}
          onChange={handleChange}
          placeholder="Title"
          required
        />
        <br />
        <textarea
          name="content"
          value={journal.content}
          onChange={handleChange}
          placeholder="Content"
          required
        />
        <br />
        <p><strong>User ID:</strong> {journal.user?.id}</p>
        <br />
        <button type="submit">Update Journal</button>
      </form>
    </div>
  );
}

export default EditJournal;
