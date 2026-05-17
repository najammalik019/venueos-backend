package com.venueos.repository;
import com.venueos.entity.Hall;
import com.venueos.enums.HallStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface HallRepository extends JpaRepository<Hall, Long> {
    List<Hall> findByStatus(HallStatus status);
}
