import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Navbar from "./components/Navbar";
import Home from "./pages/Home";
import Candidate from "./pages/Candidate";
import Recruiter from "./pages/Recruiter";

function App() {
  return (
    <Router>
      <Navbar />

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/candidate" element={<Candidate />} />
        <Route path="/recruiter" element={<Recruiter />} />
      </Routes>
    </Router>
  );
}

export default App;