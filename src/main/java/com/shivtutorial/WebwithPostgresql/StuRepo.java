package com.shivtutorial.WebwithPostgresql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StuRepo extends JpaRepository<Student, Long> {
}
