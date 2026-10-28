import { useState } from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'
import { useNavigate } from 'react-router-dom'

function Login() {
  const [empId, setEmpId] = useState('')
  const [password, setPassword] = useState('')
  const [email, setEmail] = useState('')
  const navigate = useNavigate()

  async function loginform(e) {
    e.preventDefault()

    const employeeId = Number(empId)
    if (!empId.trim() || !Number.isInteger(employeeId) || employeeId <= 0) {
      alert('Please enter a valid employee ID')
      return
    }

    try {
      const server = await fetch('http://localhost:8080/v1/user/login', {
        method: 'POST',
        body: JSON.stringify({ empId: employeeId, email: email.trim(), password }),
        headers: { 'Content-Type': 'application/json' },
      })

      if (server.ok) {
        const employee = await server.json()
        alert('Login Successful')
        navigate('/dashboard', { state: { employee } })
        return
      }

      const message = await server.text()
      alert(message || 'Invalid employee ID, email, or password')
    } catch (error) {
      alert(error.message || 'Could not connect to the server')
    }
  }

  return (
    <>
      <div className="mb-3">Login Form</div>
      <div>
        <form onSubmit={loginform}>
          <div className="form-group mb-3">
            <label htmlFor="emp_id">Emp ID</label>
            <input
              type="number"
              value={empId}
              onChange={(e) => setEmpId(e.target.value)}
              className="form-control"
              id="emp_id"
              placeholder="Enter emp id"
              required
            />
          </div>
          <div className="form-group mb-3">
            <label htmlFor="email">Email</label>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="form-control"
              id="email"
              placeholder="Enter email"
              required
            />
          </div>

          <div className="form-group mb-3">
            <label htmlFor="password">Password</label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="form-control"
              id="password"
              placeholder="Enter Password"
              required
            />
          </div>
          <button type="submit" className="btn btn-primary">Login</button>
        </form>
      </div>
    </>
  )
}

export default Login