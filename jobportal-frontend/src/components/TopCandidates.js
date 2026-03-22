import React, { useState } from "react";
import axios from "axios";

function TopCandidates() {
  const [jobId, setJobId] = useState("");
  const [candidates, setCandidates] = useState([]);

  const fetchCandidates = async () => {
    try {
      const res = await axios.get(
        `https://ai-job-portal-backend-i42z.onrender.com/applications/job/${jobId}`
      );
      setCandidates(res.data);
    } catch (err) {
      console.error(err);
      alert("Error fetching candidates");
    }
  };

  return (
    <div>
      <h2>Top Candidates</h2>

      <div className="card">

        <input
          placeholder="Enter Job ID"
          value={jobId}
          onChange={(e) => setJobId(e.target.value)}
          style={{ marginRight: "10px", padding: "8px" }}
        />

        <button className="btn" onClick={fetchCandidates}>
          Get Candidates
        </button>

      </div>

      {candidates.length === 0 ? (
        <p>No candidates found</p>
      ) : (
        candidates.map((c, index) => (
          <div className="card" key={index}>
            <p><b>User ID:</b> {c.userId}</p>
            <p><b>Match Score:</b> {c.matchScore}%</p>
          </div>
        ))
      )}
    </div>
  );
}

export default TopCandidates;