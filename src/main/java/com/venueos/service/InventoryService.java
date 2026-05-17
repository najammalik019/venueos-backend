package com.venueos.service;

import com.venueos.entity.InventoryItem;
import com.venueos.enums.InventoryCategory;
import com.venueos.enums.InventoryStatus;
import com.venueos.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public List<InventoryItem> getAll() {
        return inventoryRepository.findAll();
    }

    public List<InventoryItem> getByCategory(InventoryCategory category) {
        return inventoryRepository.findByCategory(category);
    }

    public List<InventoryItem> getLowStock() {
        return inventoryRepository.findByStatus(InventoryStatus.LOW_STOCK);
    }

    public InventoryItem save(InventoryItem item) {
        updateStatus(item);
        return inventoryRepository.save(item);
    }

    @Transactional
    public InventoryItem updateQuantity(Long id, int delta) {
        InventoryItem item = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setAvailableQuantity(item.getAvailableQuantity() + delta);
        updateStatus(item);
        return inventoryRepository.save(item);
    }

    public void delete(Long id) {
        inventoryRepository.deleteById(id);
    }

    private void updateStatus(InventoryItem item) {
        if (item.getAvailableQuantity() == 0) {
            item.setStatus(InventoryStatus.OUT_OF_STOCK);
        } else if (item.getAvailableQuantity() <= item.getTotalQuantity() * 0.2) {
            item.setStatus(InventoryStatus.LOW_STOCK);
        } else {
            item.setStatus(InventoryStatus.IN_STOCK);
        }
    }
}
