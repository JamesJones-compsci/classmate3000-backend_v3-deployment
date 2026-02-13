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

// PENNY - Added imports
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CourseResponseDTO {

    private final Long courseId;
    private final String code;
    private final String title;
    private final String instructor;
    private final List<LocalDateTime> schedule;
    private final int gradeGoal;
    private final LocalDate startWeek;

    public CourseResponseDTO(
            Long courseId,
            String code,
            String title,
            String instructor,
            List<LocalDateTime> schedule,
            int gradeGoal,
            LocalDate startWeek
    ) {
        this.courseId = courseId;
        this.code = code;
        this.title = title;
        this.instructor = instructor;
        this.schedule = schedule;
        this.gradeGoal = gradeGoal;
        this.startWeek = startWeek;
    }

    public Long getCourseId() { return courseId; }
    public String getCode() { return code; }
    public String getTitle() { return title; }
    public String getInstructor() { return instructor; }
    public List<LocalDateTime> getSchedule() { return schedule; }
    public int getGradeGoal() { return gradeGoal; }
    public LocalDate getStartWeek() { return startWeek; }
}