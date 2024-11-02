CREATE TABLE students (id BIGSERIAL PRIMARY KEY, name TEXT, serial VARCHAR(4), number VARCHAR(6), UNIQUE(serial, number));
CREATE TABLE subjects (id BIGSERIAL PRIMARY KEY, name TEXT);
CREATE TABLE progress (id BIGSERIAL PRIMARY KEY, student_id BIGINT, subject_id BIGINT, mark SMALLINT, CHECK(mark > 1 AND MARK < 6));
ALTER TABLE progress ADD CONSTRAINT students_fk FOREIGN KEY (student_id) REFERENCES public.students(id) ON DELETE CASCADE;

select * from students where id in (
  select student_id from progress where mark > 3 and subject_id = (
    select id from subjects s where "name" = 'Химия'
  )
)

select avg(mark) from progress where subject_id = (
  select id from subjects where "name" = 'Химия'
)

select avg(mark) from progress where student_id = (
  select id from students where "name" = 'Антон'
)

select count(s.id) as count, "name"
    from subjects s join progress p
        on s.id = subject_id where mark >2
    group by s.id
    order by count
    desc limit 3