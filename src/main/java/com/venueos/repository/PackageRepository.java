package com.venueos.repository;
import com.venueos.entity.PackagePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PackageRepository extends JpaRepository<PackagePlan, Long> {
    List<PackagePlan> findByActiveTrue();
}
