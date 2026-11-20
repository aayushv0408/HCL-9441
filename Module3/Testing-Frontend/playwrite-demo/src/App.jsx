import { useState } from "react";

function App() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [loggedIn, setLoggedIn] = useState(false);
  const [error, setError] = useState("");

  const [studentName, setStudentName] = useState("");
  const [students, setStudents] = useState([]);

  const handleLogin = (e) => {
    e.preventDefault();

    if (email === "admin@test.com" && password === "123456") {
      setLoggedIn(true);
      setError("");
    } else {
      setError("Invalid email or password");
    }
  };

  const handleAddStudent = (e) => {
    e.preventDefault();

    if (!studentName.trim()) return;

    setStudents([...students, studentName.trim()]);
    setStudentName("");
  };

  if (!loggedIn) {
    return (
      <div>
        <h1>Student Portal</h1>

        <h2>Login</h2>

        <form onSubmit={handleLogin}>
          <label htmlFor="email">Email</label>

          <input
            id="email"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />

          <label htmlFor="password">Password</label>

          <input
            id="password"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />

          <button type="submit">Login</button>
        </form>

        {error && <p role="alert">{error}</p>}
      </div>
    );
  }

  return (
    <div>
      <h1>Student Dashboard</h1>

      <p>Welcome Admin</p>

      <h2>Add Student</h2>

      <form onSubmit={handleAddStudent}>
        <label htmlFor="studentName">Student Name</label>

        <input
          id="studentName"
          value={studentName}
          onChange={(e) => setStudentName(e.target.value)}
        />

        <button type="submit">Add Student</button>
      </form>

      <h2>Student List</h2>

      <ul>
        {students.map((student, index) => (
          <li key={index}>{student}</li>
        ))}
      </ul>
    </div>
  );
}

export default App;
