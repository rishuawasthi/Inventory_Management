package com.hcl.inventory.controller;

import com.hcl.inventory.entity.Inventory;
import com.hcl.inventory.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(
            InventoryService inventoryService) {

        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/{inventoryId}")
    public ResponseEntity<Inventory> getInventoryById(
            @PathVariable Integer inventoryId) {

        Inventory inventory =
                inventoryService.getInventoryById(inventoryId);

        if (inventory == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(inventory);
    }

    @PostMapping
    public ResponseEntity<Inventory> addInventory(
            @Valid @RequestBody Inventory inventory) {

        Inventory savedInventory =
                inventoryService.addInventory(inventory);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedInventory);
    }

    @PutMapping("/{inventoryId}")
    public ResponseEntity<Inventory> updateInventory(
            @PathVariable Integer inventoryId,
            @Valid @RequestBody Inventory inventory) {

        Inventory updatedInventory =
                inventoryService.updateInventory(
                        inventoryId,
                        inventory
                );

        if (updatedInventory == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedInventory);
    }

    @DeleteMapping("/{inventoryId}")
    public ResponseEntity<Void> deleteInventory(
            @PathVariable Integer inventoryId) {

        boolean deleted =
                inventoryService.deleteInventory(inventoryId);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/warehouse/{warehouseId}")
    public List<Inventory> getInventoryByWarehouse(
            @PathVariable Integer warehouseId) {

        return inventoryService
                .getInventoryByWarehouse(warehouseId);
    }

    @GetMapping("/product/{productId}")
    public List<Inventory> getInventoryByProduct(
            @PathVariable Integer productId) {

        return inventoryService
                .getInventoryByProduct(productId);
    }
}