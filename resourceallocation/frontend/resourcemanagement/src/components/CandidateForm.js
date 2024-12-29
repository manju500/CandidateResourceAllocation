// src/components/BookForm.js
import React, { useState } from "react";
import axiosInstance from "../api/axiosConfig";
import "./candidate.css";

const CandidateForm = ({ onCandidateAdded }) => {
  const [id, setId] = useState("");
  const [name, setName] = useState("");
  const [experience, setExperience] = useState("");
  const [skills, setSkills] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axiosInstance.post("/candidates", { id, name, experience, skills });
      setId("");
      setName("");
      setExperience("");
      setSkills("");
      onCandidateAdded(); // Notify parent component to refresh the list
    } catch (error) {
      console.error("Error adding candidate:", error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <center>
        <h2>Candidate Entry</h2>
        <div>
          <input
            type="text"
            value={id}
            placeholder="Enter Id"
            onChange={(e) => setId(e.target.value)}
          />
        </div>
        <div>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Enter name"
          />
        </div>
        <div>
          <input
            type="text"
            value={experience}
            placeholder="Enter experience"
            onChange={(e) => setExperience(e.target.value)}
          />
        </div>
        <div>
          <input
            type="text"
            value={skills}
            placeholder="Enter skills"
            onChange={(e) => setSkills(e.target.value)}
          />
        </div>
        <button type="submit">Add Candidate</button>
      </center>
    </form>
  );
};

export default CandidateForm;
