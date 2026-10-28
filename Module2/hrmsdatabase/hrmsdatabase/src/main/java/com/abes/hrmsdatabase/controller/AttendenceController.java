package com.abes.hrmsdatabase.controller;

import com.abes.hrmsdatabase.model.Attendance;
import com.abes.hrmsdatabase.service.AttendenceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173"})
@RequestMapping("/v1/user/attendance")
public class AttendenceController {
    private final AttendenceService service;
    public AttendenceController(AttendenceService service) { this.service = service; }

    @PostMapping("/check-in/{empId}")
    public Attendance checkIn(@PathVariable Long empId) { return call(() -> service.checkIn(empId)); }
    @PostMapping("/check-out/{empId}")
    public Attendance checkOut(@PathVariable Long empId) { return call(() -> service.checkOut(empId)); }
    @PostMapping("/markAttendance")
    public Attendance markAttendance(@RequestBody Attendance attendance) { return call(() -> service.markAttendance(attendance)); }
    @GetMapping("/dashboard/{empId}")
    public List<Attendance> getAttendance(@PathVariable Long empId) { return call(() -> service.getAttendance(empId)); }
    @GetMapping("/today/{empId}")
    public Attendance getToday(@PathVariable Long empId) { return call(() -> service.getToday(empId)); }
    @GetMapping("/monthly-summary/{empId}")
    public Map<String, Object> monthlySummary(@PathVariable Long empId, @RequestParam int month, @RequestParam int year) {
        return call(() -> service.monthlySummary(empId, month, year));
    }
    private <T> T call(java.util.concurrent.Callable<T> action) {
        try { return action.call(); }
        catch (IllegalArgumentException e) { throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage()); }
        catch (IllegalStateException e) { throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage()); }
        catch (Exception e) { throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Request failed"); }
    }
}
