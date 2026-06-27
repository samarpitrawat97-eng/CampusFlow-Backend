package com.campusflow.backend.repository;
import com.campusflow.backend.model.RecruitmentDrive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitmentDriveRepository extends JpaRepository<RecruitmentDrive, Long> {
}