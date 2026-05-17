package com.venueos.controller;

import com.venueos.entity.PackagePlan;
import com.venueos.repository.PackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/packages")
@RequiredArgsConstructor
public class PackageController {

    private final PackageRepository packageRepository;

    @GetMapping
    public ResponseEntity<List<PackagePlan>> getAll() {
        return ResponseEntity.ok(packageRepository.findByActiveTrue());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackagePlan> getById(@PathVariable Long id) {
        return packageRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PackagePlan> create(@RequestBody PackagePlan plan) {
        return ResponseEntity.ok(packageRepository.save(plan));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PackagePlan> update(
            @PathVariable Long id, @RequestBody PackagePlan plan) {
        plan.setId(id);
        return ResponseEntity.ok(packageRepository.save(plan));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        packageRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
