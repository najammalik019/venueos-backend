package com.venueos.entity;

import com.venueos.enums.PackageTier;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "package_plans")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PackagePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private PackageTier tier;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "base_price", nullable = false)
    private Double basePrice;

    @Column(name = "max_guests", nullable = false)
    private Integer maxGuests;

    @Column(name = "hall_hours", nullable = false)
    private Integer hallHours;

    @ElementCollection
    @CollectionTable(name = "package_features", joinColumns = @JoinColumn(name = "package_id"))
    @Column(name = "feature")
    private List<String> features;

    @Column(name = "includes_catering")
    private boolean includesCatering;

    @Column(name = "includes_decor")
    private boolean includesDecor;

    @Column(name = "includes_av")
    private boolean includesAv;

    @Column(name = "includes_parking")
    private boolean includesParking;

    @Column(name = "includes_coordinator")
    private boolean includesCoordinator;

    @Column(name = "is_active")
    private boolean active = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
