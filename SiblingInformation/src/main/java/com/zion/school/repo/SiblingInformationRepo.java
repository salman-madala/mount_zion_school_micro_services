package com.zion.school.repo;

import com.zion.school.model.SiblingInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiblingInformationRepo extends JpaRepository<SiblingInformation,Long> {
}
