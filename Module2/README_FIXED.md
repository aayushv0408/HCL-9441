# ABES HRMS - Fixed Attendance Module

## Requirements
- Java 21
- MySQL 8.x
- Node.js 20+ (recommended)

## Database setup
Create the schema once in MySQL Workbench:
```sql
CREATE DATABASE IF NOT EXISTS abeshrms;
```
Backend defaults in `application.properties` use `root` / `root` to match the uploaded project. Prefer setting environment variables `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` for your local values.

## Start backend (Windows)
Open `hrmsdatabase/hrmsdatabase` in terminal and run:
```powershell
.\mvnw.cmd spring-boot:run
```
If the wrapper cannot download Maven, install Maven and run `mvn spring-boot:run`.

## Start frontend
Open `hrmsfrontend` in a second terminal:
```powershell
npm install
npm run dev
```
Open the URL printed by Vite (normally http://localhost:5173).

## Attendance APIs
- `POST /v1/user/attendance/check-in/{empId}`
- `POST /v1/user/attendance/check-out/{empId}`
- `GET /v1/user/attendance/today/{empId}`
- `GET /v1/user/attendance/dashboard/{empId}`
- `GET /v1/user/attendance/monthly-summary/{empId}?month=10&year=2026`
- `POST /v1/user/attendance/markAttendance` (manual/basic record creation)

## Notes
- Monthly summary counts attendance statuses already recorded. It does not infer absences from the calendar; holidays, weekly offs, approved leave and company-specific late/half-day rules need a configured work calendar before absences can be calculated reliably.
- Login remains a simple existing-project flow and is **not production-secure**. Password hashing, Spring Security, and role-based authorization should be added before real employee data is used. Do not expose this development app to the public internet.
- If you already have a populated schema, back it up before running schema updates. A new unique constraint may require cleaning duplicate employee/date attendance records first.
