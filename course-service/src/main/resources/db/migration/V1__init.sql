-- ===============================
-- Course Service - Initial Schema
-- ===============================

-- Courses table
CREATE TABLE IF NOT EXISTS courses (
  id          BIGSERIAL PRIMARY KEY,
  code        VARCHAR(10)  NOT NULL,
  title       TEXT         NOT NULL,
  instructor  TEXT         NOT NULL,
  grade_goal  INTEGER      NOT NULL,
  start_week  DATE         NOT NULL
);

-- Optional: prevent duplicate course codes (uncomment if code should be unique)
-- CREATE UNIQUE INDEX IF NOT EXISTS uq_courses_code ON courses(code);

-- Schedule table for @ElementCollection List<LocalDateTime>
CREATE TABLE IF NOT EXISTS course_schedule (
  course_id    BIGINT     NOT NULL,
  scheduled_at TIMESTAMP  NOT NULL,

  CONSTRAINT fk_course_schedule_course
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,

  -- Prevent duplicates for the same course time and makes the join table well-defined
  CONSTRAINT pk_course_schedule
    PRIMARY KEY (course_id, scheduled_at)
);

-- Helpful index for lookups by course_id
CREATE INDEX IF NOT EXISTS idx_course_schedule_course_id
  ON course_schedule(course_id);
