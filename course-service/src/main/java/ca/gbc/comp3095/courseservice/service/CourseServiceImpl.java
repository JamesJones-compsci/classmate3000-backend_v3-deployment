package ca.gbc.comp3095.courseservice.service;

/* JAMES - 
* This ensures that the controller stays thin
* Mapping happens in service
* Exceptions are domain-specific
*/


import ca.gbc.comp3095.courseservice.dto.CourseRequestDTO;
import ca.gbc.comp3095.courseservice.dto.CourseResponseDTO;
import ca.gbc.comp3095.courseservice.exception.CourseNotFoundException;
import ca.gbc.comp3095.courseservice.model.Course;
import ca.gbc.comp3095.courseservice.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// PENNY - Removed description
// PENNY - getId to getCourseId
// PENNY - Added missing fields
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseResponseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public CourseResponseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));

        return toResponseDTO(course);
    }

    @Override
    public CourseResponseDTO createCourse(CourseRequestDTO dto) {
        Course course = new Course(
                dto.getCode(),
                dto.getTitle(),
                dto.getInstructor(),
                dto.getSchedule(),
                dto.getGradeGoal(),
                dto.getStartWeek()
        );

        Course saved = courseRepository.save(course);
        return toResponseDTO(saved);
    }

    // JAMES -- New method: updateCourse() -- //
    @Override
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO dto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));

        course.setCode(dto.getCode());
        course.setTitle(dto.getTitle());
        course.setInstructor(dto.getInstructor());
        course.setSchedule(dto.getSchedule());
        course.setGradeGoal(dto.getGradeGoal());
        course.setStartWeek(dto.getStartWeek());

        Course saved = courseRepository.save(course);
        return toResponseDTO(saved);
    }

    // JAMES -- New method: deleteCourse() -- //
    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        courseRepository.delete(course);
    }

    private CourseResponseDTO toResponseDTO(Course course) {
        return new CourseResponseDTO(
                course.getCourseId(),
                course.getCode(),
                course.getTitle(),
                course.getInstructor(),
                course.getSchedule(),
                course.getGradeGoal(),
                course.getStartWeek()
        );
    }
}