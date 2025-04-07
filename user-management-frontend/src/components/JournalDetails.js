// src/components/JournalDetails.js
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "../api";

function JournalDetails() {
  const { id } = useParams();
  const [journal, setJournal] = useState(null);

  useEffect(() => {
    axios.get(`/hello/${id}`)
      .then((res) => setJournal(res.data))
      .catch((err) => console.error("Error fetching journal details:", err));
  }, [id]);

  if (!journal) return <div>Loading...</div>;

  return (
    <div>
      <h2>Journal Details</h2>
      <p><strong>Title:</strong> {journal.title}</p>
      <p><strong>Content:</strong> {journal.content}</p>
      <p><strong>User ID:</strong> {journal.user.id}</p>
    </div>
  );
}

export default JournalDetails;
