// src/components/BookList.js
import React, { useEffect, useState } from "react";
import axiosInstance from "../api/axiosConfig";
import CandidateForm from "./CandidateForm";
import "./candidate.css";

const CandidateList = () => {
  const [candidates, setCandidates] = useState([]);

  useEffect(() => {
    fetchCandidates();
  }, []);

  const fetchCandidates = async () => {
    try {
      const response = await axiosInstance.get("/candidates");
      setCandidates(response.data);
    } catch (error) {
      console.error("Error fetching candidates:", error);
    }
  };

  const deleteCandidate = async (id) => {
    try {
      await axiosInstance.delete(`/candidates/${id}`);
      fetchCandidates(); // Refresh the list of books
    } catch (error) {
      console.error("Error deleting candidate:", error);
    }
  };

  return (
    <div>
      <hr />

      <h1>Candidate Filtering</h1>
      <CandidateForm onCandidateAdded={fetchCandidates} />
      <hr />
      <h2>Candidate List</h2>
      <center>
        <table>
          <thead>
            <tr>
              <th>Resource ID</th>
              <th>Resource Name</th>
              <th>Experience(year)</th>
              <th>Skills</th>
              <th>Remove</th>
            </tr>
          </thead>
          <tbody>
            {candidates.map((candidate) => (
              <tr>
                <td>{candidate.id}</td>
                <td>{candidate.name}</td>
                <td>{candidate.experience}</td>
                <td>{candidate.skills}</td>
                <td>
                  <button onClick={() => deleteCandidate(candidate.id)}>
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </center>
      <hr />
      <h1>Eligible Students :</h1>
      <ol>
        <li>
          For Micro Service Project:
          {candidates
            .filter(
              (candidate) =>
                candidate.skills.toLowerCase().includes("java") &&
                candidate.skills.toLowerCase().includes("redis") &&
                candidate.skills.toLowerCase().includes("javascript")
            )
            .map((candidate) => candidate.name)
            .reduce((names, name) => names + ", " + name, "")}
        </li>
        <li>
          For cloud based project:
          {candidates
            .filter(
              (candidate) =>
                candidate.experience < 10 &&
                candidate.skills.toLowerCase().includes("mysql") &&
                candidate.skills.toLowerCase().includes("docker") &&
                candidate.skills.toLowerCase().includes("spring") &&
                candidate.skills.toLowerCase().includes("react")
            )
            .map((candidate) => candidate.name)
            .reduce((names, name) => names + ", " + name, "")}
        </li>
      </ol>
      <hr />
    </div>
  );
};

export default CandidateList;
