import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import Navbar from './demo_component/Navbar'
import Home from './components/Home'
import Dashboard from './components/Dashboard'
import Login from './components/Login'
import Registration from './components/Registration'

function App() {
  return <BrowserRouter><Navbar /><Routes>
    <Route path="/" element={<Home />} />
    <Route path="/login" element={<Login />} />
    <Route path="/register" element={<Registration />} />
    <Route path="/dashboard" element={<Dashboard />} />
  </Routes></BrowserRouter>
}
export default App
