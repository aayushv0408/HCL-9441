import { useCallback, useEffect, useMemo, useState } from 'react'
import { useLocation } from 'react-router-dom'

const API = 'http://localhost:8080/v1/user/attendance'
const prettyTime = (value) => value ? new Date(value).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }) : '—'
const duration = (minutes = 0) => `${Math.floor(minutes / 60)}h ${minutes % 60}m`

function Dashboard() {
  const location = useLocation()
  const employee = location.state?.employee
  const [attendance, setAttendance] = useState([])
  const [today, setToday] = useState(null)
  const [summary, setSummary] = useState(null)
  const [busy, setBusy] = useState(false)
  const [message, setMessage] = useState('')
  const now = new Date()
  const [month, setMonth] = useState(now.getMonth() + 1)
  const [year, setYear] = useState(now.getFullYear())

  const loadData = useCallback(async () => {
    if (!employee?.empId) return
    const [recordsResponse, todayResponse, summaryResponse] = await Promise.all([
      fetch(`${API}/dashboard/${employee.empId}`),
      fetch(`${API}/today/${employee.empId}`),
      fetch(`${API}/monthly-summary/${employee.empId}?month=${month}&year=${year}`),
    ])
    if (!recordsResponse.ok) throw new Error(await recordsResponse.text() || 'Could not load attendance records')
    setAttendance(await recordsResponse.json())
    if (todayResponse.ok) setToday(await todayResponse.json())
    if (summaryResponse.ok) setSummary(await summaryResponse.json())
  }, [employee, month, year])

  // Fetch remote attendance data when the employee or selected month changes.
  // eslint-disable-next-line react-hooks/set-state-in-effect
  useEffect(() => { loadData().catch((error) => setMessage(error.message || 'Could not load attendance')) }, [loadData])

  async function punch(action) {
    setBusy(true); setMessage('')
    try {
      const response = await fetch(`${API}/${action}/${employee.empId}`, { method: 'POST' })
      if (!response.ok) throw new Error(await response.text() || 'Attendance action failed')
      await loadData()
      setMessage(action === 'check-in' ? 'Check-in recorded successfully.' : 'Check-out recorded successfully.')
    } catch (error) { setMessage(error.message || 'Could not connect to the server') }
    finally { setBusy(false) }
  }

  const totalMinutes = useMemo(() => attendance.reduce((sum, item) => sum + (item.workingMinutes || 0), 0), [attendance])
  if (!employee) return <div className="container py-5"><div className="alert alert-warning">Please log in first. After login, open the dashboard again.</div></div>

  return <main className="container py-4">
    <div className="d-flex flex-wrap justify-content-between align-items-center gap-3 mb-4">
      <div><p className="text-primary fw-semibold mb-1">ABES HRMS</p><h1 className="h2 mb-1">Employee Attendance</h1><p className="text-secondary mb-0">Welcome, {employee.emp_name}</p></div>
      <div className="text-md-end"><div>{employee.email}</div><small className="text-secondary">Employee ID: {employee.empId}</small></div>
    </div>
    {message && <div className="alert alert-info" role="status">{message.replace(/^\d+\s*:\s*/, '')}</div>}
    <section className="card shadow-sm border-0 mb-4"><div className="card-body p-4 d-flex flex-wrap justify-content-between align-items-center gap-3">
      <div><h2 className="h5">Today's attendance</h2><p className="text-secondary mb-2">{new Date().toLocaleDateString(undefined, { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' })}</p>
        <div className="d-flex flex-wrap gap-3"><span><strong>Check-in:</strong> {prettyTime(today?.inTime)}</span><span><strong>Check-out:</strong> {prettyTime(today?.outTime)}</span><span><strong>Worked:</strong> {duration(today?.workingMinutes || 0)}</span></div></div>
      <div className="d-flex gap-2"><button className="btn btn-primary" disabled={busy || Boolean(today)} onClick={() => punch('check-in')}>Check in</button><button className="btn btn-outline-primary" disabled={busy || !today || Boolean(today?.outTime)} onClick={() => punch('check-out')}>Check out</button></div>
    </div></section>
    <div className="d-flex flex-wrap align-items-end gap-3 mb-3"><div><label className="form-label" htmlFor="month">Month</label><select id="month" className="form-select" value={month} onChange={(e) => setMonth(Number(e.target.value))}>{Array.from({ length: 12 }, (_, i) => <option key={i + 1} value={i + 1}>{new Date(2000, i, 1).toLocaleString(undefined, { month: 'long' })}</option>)}</select></div><div><label className="form-label" htmlFor="year">Year</label><input id="year" type="number" min="2000" max="2100" className="form-control" value={year} onChange={(e) => setYear(Number(e.target.value))}/></div></div>
    <div className="row g-3 mb-4">{[['Attendance records', summary?.recordCount ?? 0], ['Present days', summary?.presentDays ?? 0], ['Half-days', summary?.halfDays ?? 0], ['Late arrivals', summary?.lateArrivals ?? 0], ['Absent days recorded', summary?.absentDays ?? 0], ['Total working time', duration(summary?.totalWorkingMinutes ?? totalMinutes)]].map(([label, value]) => <div className="col-6 col-lg-4" key={label}><div className="card border-0 shadow-sm h-100"><div className="card-body"><div className="text-secondary small">{label}</div><div className="fs-3 fw-semibold">{value}</div></div></div></div>)}</div>
    <section className="card shadow-sm border-0"><div className="card-body"><h2 className="h5 mb-3">Attendance history</h2><div className="table-responsive"><table className="table table-hover align-middle"><thead><tr><th>Date</th><th>Check-in</th><th>Check-out</th><th>Duration</th><th>Status</th><th>Comment</th></tr></thead><tbody>{attendance.filter((item) => { const d = item.attendancedate || ''; return d.startsWith(`${year}-${String(month).padStart(2, '0')}`) }).map((item) => <tr key={item.attendanceId}><td>{item.attendancedate}</td><td>{prettyTime(item.inTime)}</td><td>{prettyTime(item.outTime)}</td><td>{duration(item.workingMinutes || 0)}</td><td><span className="badge text-bg-light">{item.status || 'PRESENT'}</span></td><td>{item.comment || '—'}</td></tr>)}</tbody></table></div>{attendance.length === 0 && <p className="text-secondary mb-0">No attendance records found.</p>}</div></section>
  </main>
}
export default Dashboard
