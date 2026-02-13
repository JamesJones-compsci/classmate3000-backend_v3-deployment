package ca.gbc.comp3095.courseservice.dto;

/* JAMES - 
* Purpose:
*        - Prevent exposing internal DB structure
*        - Allow API evolution without breaking clients
*
* Request DTO has validation
* Response DTO is read-only
* No setters in response DTO -> immutability
*/

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Penny - New imports
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CourseRequestDTO {

    @NotBlank(message = "Course code is required")
    @Size(max = 10, message = "Course code must be at most 10 characters")
    private String code;

    @NotBlank(message = "Course title is required")
    private String title;

    @NotBlank(message = "Instructor is required")
    private String instructor;

    @NotNull(message = "Schedule is required")
    private List<LocalDateTime> schedule;

    @NotNull(message = "Grade goal is required")
    private Integer gradeGoal;                      // PENNY - needs to be Integer for NotNull to work

    @NotNull(message = "Start week is required")
    private LocalDate startWeek;

    // Getters and Setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    public List<LocalDateTime> getSchedule() { return schedule; }
    public void setSchedule(List<LocalDateTime> schedule) { this.schedule = schedule; }

    public Integer getGradeGoal() { return gradeGoal; }
    public void setGradeGoal(Integer gradeGoal) { this.gradeGoal = gradeGoal; }

    public LocalDate getStartWeek() { return startWeek; }
    public void setStartWeek(LocalDate startWeek) { this.startWeek = startWeek; }
}