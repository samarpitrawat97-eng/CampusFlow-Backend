package com.campusflow.backend.repository;
import com.campusflow.backend.model.CandidateApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateApplicationRepository extends JpaRepository<CandidateApplication, Long> {
}