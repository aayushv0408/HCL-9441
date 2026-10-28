# HRMS fixes included

## Fixed
- Corrected attendance repository nested employee property names.
- Corrected attendance API URL usage and CORS origins.
- Added check-in and check-out workflows with duplicate and time validation.
- Added today's attendance and monthly summary APIs.
- Added attendance status and working-minute calculation.
- Fixed registration JSON employee ID (`empId`) and improved frontend errors.
- Fixed employee update to save the retrieved entity.
- Fixed employee delete endpoint path-variable binding and HTTP method.
- Prevented password serialization in employee API responses.
- Removed duplicate BrowserRouter setup and cleaned unused app imports.
- Replaced the incompatible Java 25/Spring Boot 4 starter setup with Java 21/Spring Boot 3.5.x-compatible dependencies.
- Moved DB credentials to environment-variable overrides while keeping the original local defaults.
- Added setup instructions.

## Validation performed
- ESLint passed for the changed React components.
- Full Vite production build could not be completed in this environment because the ZIP's existing `node_modules` lacked the platform-specific Rolldown native binding. Run `npm install` in `hrmsfrontend` on your machine, then `npm run build`.
- Backend Maven package could not be verified because the Maven wrapper could not download Maven in this environment. Run `./mvnw.cmd test` (Windows) from `hrmsdatabase/hrmsdatabase`.

## Important limitations
This is a development project, not production-secure: login still uses plain-text password comparison and attendance endpoints accept employee IDs in the URL. Before real use, add password hashing, Spring Security, role-based authorization, and derive the employee identity from the authenticated session/token. Absence percentages require a company work calendar, weekly-off/holiday rules and leave integration; the monthly summary currently reports statuses already recorded.
