import { Link } from "react-router-dom";

function Navbar() {
  return (
    <nav style={{ padding: "15px", background: "#222", color: "#fff" }}>
      <h2>Job Portal</h2>
      <Link to="/" style={{ marginRight: "10px", color: "#fff" }}>Home</Link>
      <Link to="/candidate" style={{ marginRight: "10px", color: "#fff" }}>Jobs</Link>
      <Link to="/recruiter" style={{ color: "#fff" }}>Recruiter</Link>
    </nav>
  );
}

export default Navbar;