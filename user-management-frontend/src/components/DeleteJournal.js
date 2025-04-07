// src/components/DeleteJournal.js
import React, { useEffect } from "react";
import axios from "../api";
import { useParams, useNavigate } from "react-router-dom";

function DeleteJournal() {
  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    const deleteJournal = async () => {
      if (window.confirm("Are you sure you want to delete this journal?")) {
        try {
          await axios.delete(`/hello/delete/${id}`);
          alert("Journal deleted successfully!");
          navigate("/journals");
        } catch (error) {
          alert("Error deleting journal!");
          console.error(error);
        }
      } else {
        navigate("/journals");
      }
    };

    deleteJournal();
  }, [id, navigate]);

  return <p>Deleting journal...</p>;
}

export default DeleteJournal;
