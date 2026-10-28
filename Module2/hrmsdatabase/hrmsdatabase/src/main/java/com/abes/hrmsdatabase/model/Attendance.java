package com.abes.hrmsdatabase.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance", uniqueConstraints = @UniqueConstraint(name = "uk_attendance_employee_date", columnNames = {"empid", "attendancedate"}))
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendenceid")
    private Long attendanceId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "empid", nullable = false)
    @JsonIgnoreProperties({"password"})
    private Employee employee;

    @Column(name = "attendancedate", nullable = false)
    private LocalDate attendancedate;
    private LocalDateTime inTime;
    private LocalDateTime outTime;

    @Enumerated(EnumType.STRING)
    @Column(length = 24)
    private AttendanceStatus status = AttendanceStatus.PRESENT;

    @Column(length = 500)
    private String comment;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Attendance() {}
    @PrePersist void onCreate() { createdAt = LocalDateTime.now(); updatedAt = createdAt; }
    @PreUpdate void onUpdate() { updatedAt = LocalDateTime.now(); }

    @Transient public Long getWorkingMinutes() {
        if (inTime == null) return 0L;
        LocalDateTime end = outTime == null ? LocalDateTime.now() : outTime;
        return Math.max(0, Duration.between(inTime, end).toMinutes());
    }
    public Long getAttendanceId() { return attendanceId; }
    public void setAttendanceId(Long attendanceId) { this.attendanceId = attendanceId; }
    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    public LocalDate getAttendancedate() { return attendancedate; }
    public void setAttendancedate(LocalDate attendancedate) { this.attendancedate = attendancedate; }
    public LocalDateTime getInTime() { return inTime; }
    public void setInTime(LocalDateTime inTime) { this.inTime = inTime; }
    public LocalDateTime getOutTime() { return outTime; }
    public void setOutTime(LocalDateTime outTime) { this.outTime = outTime; }
    public AttendanceStatus getStatus() { return status; }
    public void setStatus(AttendanceStatus status) { this.status = status; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
