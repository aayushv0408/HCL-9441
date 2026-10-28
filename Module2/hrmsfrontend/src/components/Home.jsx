import { Link } from 'react-router-dom'
function Home() {
  return <main className="container py-5"><div className="p-4 p-md-5 rounded-4 bg-light"><span className="badge text-bg-primary mb-3">Human Resource Management</span><h1 className="display-5 fw-bold">Attendance, made simple.</h1><p className="lead text-secondary">Check in, check out, and review your attendance history from one place.</p><div className="d-flex gap-2 flex-wrap mt-4"><Link className="btn btn-primary btn-lg" to="/login">Employee login</Link><Link className="btn btn-outline-secondary btn-lg" to="/register">Register employee</Link></div></div></main>
}
export default Home
