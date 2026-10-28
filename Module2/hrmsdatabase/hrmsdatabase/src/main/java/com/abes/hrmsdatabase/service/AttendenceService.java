package com.abes.hrmsdatabase.service;

import com.abes.hrmsdatabase.model.Attendance;
import com.abes.hrmsdatabase.model.AttendanceStatus;
import com.abes.hrmsdatabase.model.Employee;
import com.abes.hrmsdatabase.repository.AttendenceRepository;
import com.abes.hrmsdatabase.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttendenceService {
    private final AttendenceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public AttendenceService(AttendenceRepository attendanceRepository, EmployeeRepository employeeRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public Attendance checkIn(Long empId) {
        if (empId == null) throw new IllegalArgumentException("Employee ID is required");
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new IllegalArgumentException("Employee does not exist"));
        LocalDate today = LocalDate.now();
        if (attendanceRepository.existsByEmployee_EmpIdAndAttendancedate(empId, today))
            throw new IllegalStateException("Attendance already exists for today");
        Attendance attendance = new Attendance();
        attendance.setEmployee(employee);
        attendance.setAttendancedate(today);
        attendance.setInTime(LocalDateTime.now());
        attendance.setStatus(AttendanceStatus.PRESENT);
        return attendanceRepository.save(attendance);
    }

    @Transactional
    public Attendance checkOut(Long empId) {
        LocalDate today = LocalDate.now();
        Attendance attendance = attendanceRepository.findByEmployee_EmpIdAndAttendancedate(empId, today)
                .orElseThrow(() -> new IllegalStateException("Check in before checking out"));
        if (attendance.getOutTime() != null) throw new IllegalStateException("Already checked out today");
        LocalDateTime now = LocalDateTime.now();
        if (attendance.getInTime() == null || now.isBefore(attendance.getInTime()))
            throw new IllegalStateException("Check-out time cannot be before check-in time");
        attendance.setOutTime(now);
        return attendanceRepository.save(attendance);
    }

    @Transactional
    public Attendance markAttendance(Attendance input) {
        if (input == null || input.getEmployee() == null || input.getEmployee().getEmpId() == null)
            throw new IllegalArgumentException("Employee ID is required");
        Long empId = input.getEmployee().getEmpId();
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new IllegalArgumentException("Employee does not exist"));
        LocalDate date = input.getAttendancedate() == null ? LocalDate.now() : input.getAttendancedate();
        if (attendanceRepository.existsByEmployee_EmpIdAndAttendancedate(empId, date))
            throw new IllegalStateException("Attendance already exists for this date");
        if (input.getInTime() != null && input.getOutTime() != null && input.getOutTime().isBefore(input.getInTime()))
            throw new IllegalArgumentException("Check-out cannot be before check-in");
        input.setAttendanceId(null);
        input.setEmployee(employee);
        input.setAttendancedate(date);
        if (input.getStatus() == null) input.setStatus(AttendanceStatus.PRESENT);
        return attendanceRepository.save(input);
    }

    public List<Attendance> getAttendance(Long empId) {
        if (!employeeRepository.existsById(empId)) throw new IllegalArgumentException("Employee does not exist");
        return attendanceRepository.findByEmployee_EmpIdOrderByAttendancedateDesc(empId);
    }

    public Attendance getToday(Long empId) {
        if (!employeeRepository.existsById(empId)) throw new IllegalArgumentException("Employee does not exist");
        return attendanceRepository.findByEmployee_EmpIdAndAttendancedate(empId, LocalDate.now()).orElse(null);
    }

    public Map<String, Object> monthlySummary(Long empId, int month, int year) {
        if (!employeeRepository.existsById(empId)) throw new IllegalArgumentException("Employee does not exist");
        YearMonth ym;
        try { ym = YearMonth.of(year, month); } catch (RuntimeException ex) { throw new IllegalArgumentException("Month must be 1-12 and year must be valid"); }
        List<Attendance> records = attendanceRepository.findByEmployee_EmpIdAndAttendancedateBetweenOrderByAttendancedateDesc(empId, ym.atDay(1), ym.atEndOfMonth());
        long present = records.stream().filter(a -> a.getStatus() == AttendanceStatus.PRESENT || a.getStatus() == AttendanceStatus.LATE).count();
        long halfDays = records.stream().filter(a -> a.getStatus() == AttendanceStatus.HALF_DAY).count();
        long absent = records.stream().filter(a -> a.getStatus() == AttendanceStatus.ABSENT).count();
        long late = records.stream().filter(a -> a.getStatus() == AttendanceStatus.LATE).count();
        long workingMinutes = records.stream().mapToLong(Attendance::getWorkingMinutes).sum();
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("employeeId", empId); result.put("month", month); result.put("year", year);
        result.put("recordCount", records.size()); result.put("presentDays", present);
        result.put("halfDays", halfDays); result.put("absentDays", absent); result.put("lateArrivals", late);
        result.put("totalWorkingMinutes", workingMinutes); result.put("records", records);
        return result;
    }
}
