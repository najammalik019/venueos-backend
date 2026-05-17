package com.venueos.repository;
import com.venueos.entity.InventoryItem;
import com.venueos.enums.InventoryCategory;
import com.venueos.enums.InventoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
    List<InventoryItem> findByCategory(InventoryCategory category);
    List<InventoryItem> findByStatus(InventoryStatus status);
}
