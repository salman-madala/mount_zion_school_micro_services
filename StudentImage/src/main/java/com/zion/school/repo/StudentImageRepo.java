package com.zion.school.repo;

import com.zion.school.model.StudentImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentImageRepo extends JpaRepository<StudentImage,Long> {
}
