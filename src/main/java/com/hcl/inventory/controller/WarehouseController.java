package com.hcl.inventory.controller;

import com.hcl.inventory.entity.Warehouse;
import com.hcl.inventory.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    // GET /warehouses
    @GetMapping
    public List<Warehouse> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    // GET /warehouses/{id}
    @GetMapping("/{warehouseId}")
    public ResponseEntity<Warehouse> getWarehouseById(
            @PathVariable Integer warehouseId) {

        Warehouse warehouse =
                warehouseService.getWarehouseById(warehouseId);

        if (warehouse == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(warehouse);
    }

    // POST /warehouses
    @PostMapping
    public ResponseEntity<Warehouse> addWarehouse(
            @Valid @RequestBody Warehouse warehouse) {

        Warehouse savedWarehouse =
                warehouseService.addWarehouse(warehouse);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedWarehouse);
    }

    // PUT /warehouses/{id}
    @PutMapping("/{warehouseId}")
    public ResponseEntity<Warehouse> updateWarehouse(
            @PathVariable Integer warehouseId,
            @Valid @RequestBody Warehouse warehouse) {

        Warehouse updatedWarehouse =
                warehouseService.updateWarehouse(
                        warehouseId,
                        warehouse
                );

        if (updatedWarehouse == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedWarehouse);
    }

    // DELETE /warehouses/{id}
    @DeleteMapping("/{warehouseId}")
    public ResponseEntity<Void> deleteWarehouse(
            @PathVariable Integer warehouseId) {

        boolean deleted =
                warehouseService.deleteWarehouse(warehouseId);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}