CREATE TABLE IF NOT EXISTS courses (
  id BIGSERIAL PRIMARY KEY,
  code VARCHAR(10) NOT NULL,
  title TEXT NOT NULL,
  instructor TEXT NOT NULL,
  grade_goal INTEGER NOT NULL,
  start_week DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS course_schedule (
  course_id BIGINT NOT NULL REFERENCES courses(id) ON DELETE CASCADE,
  scheduled_at TIMESTAMP NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_course_schedule_course_id
  ON course_schedule(course_id);
