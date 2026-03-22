function JobCard({ job }) {
  return (
    <div style={{
      border: "1px solid #ddd",
      padding: "15px",
      marginBottom: "10px",
      borderRadius: "10px",
      background: "#f9f9f9"
    }}>
      <h3>{job.title}</h3>
      <p><b>Company:</b> {job.company}</p>
      <p><b>Location:</b> {job.location}</p>
    </div>
  );
}

export default JobCard;