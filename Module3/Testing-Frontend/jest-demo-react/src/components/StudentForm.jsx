import { useState } from "react";

const StudentForm = () => {
  const [name, setName] = useState("");
  const [message, setMessage] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!name.trim()) {
      setMessage("Please enter student name");
      return;
    }

    setMessage(`Student ${name} added successfully`);

    setName("");
  };

  return (
    <div>
      <h2>Student Registration</h2>

      <form onSubmit={handleSubmit}>
        <label htmlFor="studentName">Student Name</label>

        <input
          id="studentName"
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
          placeholder="Enter student name"
        />

        <button type="submit">Register Student</button>
      </form>

      {message && <p role="status">{message}</p>}
    </div>
  );
};

export default StudentForm;