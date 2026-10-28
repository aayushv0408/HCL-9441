import { Link, useLocation } from 'react-router-dom'
function Navbar() {
  const location = useLocation()
  return <nav className="navbar navbar-expand-lg navbar-dark bg-dark px-3 px-lg-5">
    <Link className="navbar-brand fw-bold" to="/">ABES HRMS</Link>
    <div className="navbar-nav ms-auto flex-row gap-3">
      <Link className={`nav-link ${location.pathname === '/' ? 'active' : ''}`} to="/">Home</Link>
      <Link className={`nav-link ${location.pathname === '/register' ? 'active' : ''}`} to="/register">Register</Link>
      <Link className={`nav-link ${location.pathname === '/login' ? 'active' : ''}`} to="/login">Login</Link>
    </div>
  </nav>
}
export default Navbar
