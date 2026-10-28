import { useState } from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';

function Registration() {
  const [emp_id, setEmpId] = useState('')
  const [emp_name, setEmpName] = useState('')
  const [email, setEmail] = useState('')
  const [designation, setDesignation] = useState('')
  const [stack, setStack] = useState('')
  const [password, setPassword] = useState('')

  async function sendData(e) {
    try {
      e.preventDefault();
      const server = await fetch("http://localhost:8080/v1/user/addNewEmployee", {
        method: 'POST',
        body: JSON.stringify({ empId: Number(emp_id), emp_name, email, designation, stack, password }),
        headers: { "Content-Type": "application/json" }
      });
      const status = await server.text();
      if (!server.ok) throw new Error(status || "Registration failed");
      alert(status)
    }
    catch (error) {
      alert(error.message || "Could not connect to the server");
    }
  }

  return (
    <>
      <div>Registration From</div>

      <form onSubmit={sendData}>
        <div className="form-group">
          <label htmlFor="emp_id">Emp ID</label>
          <input type="number" onChange={(e) => setEmpId(e.target.value)} className="form-control" id="emp_id" placeholder="Enter emp id" required />
        </div>

        <div className="form-group">
          <label htmlFor="emp_name">Name</label>
          <input type="text" onChange={(e) => setEmpName(e.target.value)} className="form-control" id="emp_name" placeholder="Enter name" required />
        </div>

        <div className="form-group">
          <label htmlFor="email">Email</label>
          <input type="email" onChange={(e) => setEmail(e.target.value)} className="form-control" id="email" placeholder="Enter email" required />
        </div>

        <div className="form-group">
          <label htmlFor="designation">Designation</label>
          <input type="text" onChange={(e) => setDesignation(e.target.value)} className="form-control" id="designation" placeholder="Enter designation" required />
        </div>

        <div className="form-group">
          <label htmlFor="stack">Stack</label>
          <input type="text" onChange={(e) => setStack(e.target.value)} className="form-control" id="stack" placeholder="Enter stack" required />
        </div>

        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input type="password" onChange={(e) => setPassword(e.target.value)} className="form-control" id="password" placeholder="Enter password" required />
        </div>

        <button type="submit" className="btn btn-primary">Submit</button>
      </form>
    </>
  )
}

export default Registration 