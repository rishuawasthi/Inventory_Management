package com.hcl.inventory.repository;

import com.hcl.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository
        extends JpaRepository<Inventory, Integer> {

    List<Inventory> findByWarehouseWarehouseId(
            Integer warehouseId
    );

    List<Inventory> findByProductProductId(
            Integer productId
    );
}