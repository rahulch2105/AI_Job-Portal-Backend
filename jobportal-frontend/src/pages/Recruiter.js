import { useState } from "react";
import api from "../api";

function Recruiter() {
  const [job, setJob] = useState({
    title: "",
    company: "",
    location: ""
  });

  const handleSubmit = (e) => {
    e.preventDefault();

    api.post("/jobs", job)
      .then(() => alert("Job posted successfully"))
      .catch(err => console.log(err));
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Post a Job</h2>

      <form onSubmit={handleSubmit}>
        <input
          placeholder="Title"
          onChange={(e) => setJob({ ...job, title: e.target.value })}
        /><br /><br />

        <input
          placeholder="Company"
          onChange={(e) => setJob({ ...job, company: e.target.value })}
        /><br /><br />

        <input
          placeholder="Location"
          onChange={(e) => setJob({ ...job, location: e.target.value })}
        /><br /><br />

        <button type="submit">Post Job</button>
      </form>
    </div>
  );
}

export default Recruiter;