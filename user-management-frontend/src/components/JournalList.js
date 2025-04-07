// src/components/JournalList.js
import React, { useEffect, useState } from "react";
import axios from "../api";
import { Link } from "react-router-dom";

function JournalList() {
  const [journals, setJournals] = useState([]);

  useEffect(() => {
    axios.get("/hello/abc")
      .then((res) => setJournals(res.data))
      .catch((err) => console.error("Failed to load journals:", err));
  }, []);

  return (
    <div>
      <h2>All Journal Entries</h2>
      <table border="1" cellPadding="10">
        <thead>
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Content</th>
            <th>User ID</th>
            <th>Action</th> {/* 👈 New column for View */}
          </tr>
        </thead>
        <tbody>
          {journals.map((journal) => (
            <tr key={journal.id}>
              <td>{journal.id}</td>
              <td>{journal.title}</td>
              <td>{journal.content}</td>
              <td>{journal.user?.id}</td>
              <td>
                <Link to={`/journals/${journal.id}`}>View</Link>

                <td>
                  <Link to={`/journals/edit/${journal.id}`}>Edit</Link>

                    <td>
                    <Link to={`/journals/delete/${journal.id}`}>Delete</Link>
                     </td>
                     </td>
              </td> {/* 👈 View Link */}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default JournalList;
