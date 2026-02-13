package ca.gbc.comp3095.courseservice.model;

// JAMES - Purpose: Represent how course data is stored internally, NOT how it's exposed

// JAMES - @Entity -> JPA-managed persistence
// JAMES - @Table(name = "courses") -> explicit DB mapping
// JAMES - No validation annotations here -> entities are NOT API contracts

import jakarta.persistence.*;

// PENNY - Added imports we need for the types used for the new properties
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// PENNY - Added all the properties based on updated UML - removed 'description' property
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;  // PENNY - Renamed to courseId - TODO: What else is affected by this

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String instructor;

    // PENNY - List of meeting times / scheduled class times
    // PENNY - Stored as a separate table: course_schedule(course_id, scheduled_at)
    @ElementCollection
    @CollectionTable(
            name = "course_schedule",
            joinColumns = @JoinColumn(name = "course_id")
    )
    @Column(name = "scheduled_at", nullable = false)
    private List<LocalDateTime> schedule = new ArrayList<>();

    @Column(nullable = false)
    private int gradeGoal;

    @Column(nullable = false)
    private LocalDate startWeek;

    // Constructors
    public Course() {}

    public Course(String code, String title, String instructor,
                  List<LocalDateTime> schedule, int gradeGoal, LocalDate startWeek) {
        this.code = code;
        this.title = title;
        this.instructor = instructor;
        if (schedule != null) this.schedule = schedule;
        this.gradeGoal = gradeGoal;
        this.startWeek = startWeek;
    }

    // Getters and Setters
    public Long getCourseId() {
        return courseId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public List<LocalDateTime> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<LocalDateTime> schedule) {
        this.schedule = (schedule != null) ? schedule : new ArrayList<>();
    }

    public int getGradeGoal() {
        return gradeGoal;
    }

    public void setGradeGoal(int gradeGoal) {
        this.gradeGoal = gradeGoal;
    }

    public LocalDate getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(LocalDate startWeek) {
        this.startWeek = startWeek;
    }
}