package ca.gbc.comp3095.courseprogressservice.service;

import ca.gbc.comp3095.courseprogressservice.dto.CourseProgressRequestDTO;
import ca.gbc.comp3095.courseprogressservice.dto.CourseProgressResponseDTO;
import ca.gbc.comp3095.courseprogressservice.exception.CourseProgressNotFoundException;
import ca.gbc.comp3095.courseprogressservice.model.CourseProgress;
import ca.gbc.comp3095.courseprogressservice.repository.CourseProgressRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseProgressServiceImpl implements CourseProgressService {

    private final CourseProgressRepository repository;

    public CourseProgressServiceImpl(CourseProgressRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CourseProgressResponseDTO> getAllProgress() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public CourseProgressResponseDTO getProgressById(Long id) {
        CourseProgress progress = repository.findById(id)
                .orElseThrow(() -> new CourseProgressNotFoundException(id));
        return toResponseDTO(progress);
    }

    @Override
    public CourseProgressResponseDTO createProgress(CourseProgressRequestDTO dto) {
        CourseProgress progress = new CourseProgress(
                dto.getCourseId(),
                dto.getAccumulatedPercentPoints(),
                dto.getUsedPercentPoints(),
                dto.getLostPercentPoints(),
                dto.getMaxPossiblePercent(),
                dto.getCurrentGradePercent(),
                dto.isCanMeetGoal(),
                dto.getWeekOf(),
                dto.getComputedAt() != null ? dto.getComputedAt() : LocalDateTime.now()
        );

        CourseProgress saved = repository.save(progress);
        return toResponseDTO(saved);
    }

    @Override
    public CourseProgressResponseDTO updateProgress(Long id, CourseProgressRequestDTO dto) {
        CourseProgress progress = repository.findById(id)
                .orElseThrow(() -> new CourseProgressNotFoundException(id));

        progress.setCourseId(dto.getCourseId());
        progress.setAccumulatedPercentPoints(dto.getAccumulatedPercentPoints());
        progress.setUsedPercentPoints(dto.getUsedPercentPoints());
        progress.setLostPercentPoints(dto.getLostPercentPoints());
        progress.setMaxPossiblePercent(dto.getMaxPossiblePercent());
        progress.setCurrentGradePercent(dto.getCurrentGradePercent());
        progress.setCanMeetGoal(dto.isCanMeetGoal());
        progress.setWeekOf(dto.getWeekOf());
        progress.setComputedAt(dto.getComputedAt() != null ? dto.getComputedAt() : progress.getComputedAt());

        CourseProgress saved = repository.save(progress);
        return toResponseDTO(saved);
    }

    @Override
    public void deleteProgress(Long id) {
        CourseProgress progress = repository.findById(id)
                .orElseThrow(() -> new CourseProgressNotFoundException(id));
        repository.delete(progress);
    }

    private CourseProgressResponseDTO toResponseDTO(CourseProgress p) {
        return new CourseProgressResponseDTO(
                p.getProgressId(),
                p.getCourseId(),
                p.getAccumulatedPercentPoints(),
                p.getUsedPercentPoints(),
                p.getLostPercentPoints(),
                p.getMaxPossiblePercent(),
                p.getCurrentGradePercent(),
                p.isCanMeetGoal(),
                p.getWeekOf(),
                p.getComputedAt()
        );
    }
}
