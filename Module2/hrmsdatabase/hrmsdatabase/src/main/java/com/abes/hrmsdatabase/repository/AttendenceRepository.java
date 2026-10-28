package com.abes.hrmsdatabase.repository;

import com.abes.hrmsdatabase.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendenceRepository extends JpaRepository<Attendance, Long> {
    boolean existsByEmployee_EmpIdAndAttendancedate(Long empId, LocalDate date);
    Optional<Attendance> findByEmployee_EmpIdAndAttendancedate(Long empId, LocalDate date);
    List<Attendance> findByEmployee_EmpIdOrderByAttendancedateDesc(Long empId);
    List<Attendance> findByEmployee_EmpIdAndAttendancedateBetweenOrderByAttendancedateDesc(Long empId, LocalDate start, LocalDate end);
}
