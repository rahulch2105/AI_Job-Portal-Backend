import { useEffect, useState } from "react";
import api from "../api";
import JobCard from "./JobCard";

function JobList() {
  const [jobs, setJobs] = useState([]);

  useEffect(() => {
    api.get("/jobs")
      .then(res => setJobs(res.data))
      .catch(err => console.log(err));
  }, []);

  return (
    <div>
      <h2>Available Jobs</h2>

      {jobs.length === 0 ? (
        <p>No jobs found</p>
      ) : (
        jobs.map(job => <JobCard key={job.id} job={job} />)
      )}
    </div>
  );
}

export default JobList;