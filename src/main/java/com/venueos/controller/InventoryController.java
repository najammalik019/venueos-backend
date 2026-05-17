package com.venueos.controller;

import com.venueos.entity.InventoryItem;
import com.venueos.enums.InventoryCategory;
import com.venueos.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryItem>> getAll() {
        return ResponseEntity.ok(inventoryService.getAll());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<InventoryItem>> getByCategory(
            @PathVariable InventoryCategory category) {
        return ResponseEntity.ok(inventoryService.getByCategory(category));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<InventoryItem>> getLowStock() {
        return ResponseEntity.ok(inventoryService.getLowStock());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<InventoryItem> create(@RequestBody InventoryItem item) {
        return ResponseEntity.ok(inventoryService.save(item));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<InventoryItem> update(
            @PathVariable Long id, @RequestBody InventoryItem item) {
        item.setId(id);
        return ResponseEntity.ok(inventoryService.save(item));
    }

    @PatchMapping("/{id}/quantity")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','STAFF')")
    public ResponseEntity<InventoryItem> updateQuantity(
            @PathVariable Long id, @RequestParam int delta) {
        return ResponseEntity.ok(inventoryService.updateQuantity(id, delta));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inventoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
