import React, { useState } from "react";
import { applyJob } from "../api";

function ApplyForm() {
  const [form, setForm] = useState({
    name: "",
    email: "",
    jobId: "",
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    await applyJob(form);
    alert("Applied successfully!");
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Apply</h2>

      <input
        placeholder="Name"
        onChange={(e) => setForm({ ...form, name: e.target.value })}
      />

      <input
        placeholder="Email"
        onChange={(e) => setForm({ ...form, email: e.target.value })}
      />

      <input
        placeholder="Job ID"
        onChange={(e) => setForm({ ...form, jobId: e.target.value })}
      />

      <button type="submit">Apply</button>
    </form>
  );
}

export default ApplyForm;