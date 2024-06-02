-- list St. Louis employer names in no specific order
SELECT name FROM employer WHERE location = "St. Louis City";

DROP TABLE job;

-- list skill names attached to jobs in alphabetical order
SELECT * FROM skill
JOIN job_skills
ON skill.id = job_skills.skills_id
WHERE job_skills.jobs_id IS NOT NULL
ORDER BY name ASC;