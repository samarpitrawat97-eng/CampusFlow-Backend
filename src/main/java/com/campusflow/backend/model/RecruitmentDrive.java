package com.campusflow.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "recruitment_drives")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitmentDrive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String roleName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String eligibilityCriteria;

    @Column(nullable = false)
    private LocalDateTime applicationDeadline;

    // Weightages for the final score matrix (must sum to 100)
    @Column(nullable = false)
    private Integer cvWeightage;

    @Column(nullable = false)
    private Integer aiRoundWeightage;

    @Column(nullable = false)
    private Integer piRoundWeightage;

    // The Org hosting this drive
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id", nullable = false)
    private Org org;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}