package com.hcl.inventory.service;

import com.hcl.inventory.entity.Inventory;
import com.hcl.inventory.entity.Product;
import com.hcl.inventory.entity.Warehouse;
import com.hcl.inventory.repository.InventoryRepository;
import com.hcl.inventory.repository.ProductRepository;
import com.hcl.inventory.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public InventoryService(
            InventoryRepository inventoryRepository,
            ProductRepository productRepository,
            WarehouseRepository warehouseRepository) {

        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
    }

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Integer inventoryId) {
        return inventoryRepository
                .findById(inventoryId)
                .orElse(null);
    }

    public Inventory addInventory(Inventory inventory) {

        Integer productId =
                inventory.getProduct().getProductId();

        Integer warehouseId =
                inventory.getWarehouse().getWarehouseId();

        Product product =
                productRepository.findById(productId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Product with id "
                                                + productId
                                                + " not found"
                                )
                        );

        Warehouse warehouse =
                warehouseRepository.findById(warehouseId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Warehouse with id "
                                                + warehouseId
                                                + " not found"
                                )
                        );

        inventory.setProduct(product);
        inventory.setWarehouse(warehouse);

        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventory(
            Integer inventoryId,
            Inventory inventory) {

        Inventory existingInventory =
                inventoryRepository
                        .findById(inventoryId)
                        .orElse(null);

        if (existingInventory == null) {
            return null;
        }

        Integer productId =
                inventory.getProduct().getProductId();

        Integer warehouseId =
                inventory.getWarehouse().getWarehouseId();

        Product product =
                productRepository.findById(productId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Product with id "
                                                + productId
                                                + " not found"
                                )
                        );

        Warehouse warehouse =
                warehouseRepository.findById(warehouseId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Warehouse with id "
                                                + warehouseId
                                                + " not found"
                                )
                        );

        existingInventory.setProduct(product);
        existingInventory.setWarehouse(warehouse);
        existingInventory.setQuantity(inventory.getQuantity());

        return inventoryRepository.save(existingInventory);
    }

    public boolean deleteInventory(Integer inventoryId) {

        if (!inventoryRepository.existsById(inventoryId)) {
            return false;
        }

        inventoryRepository.deleteById(inventoryId);
        return true;
    }

    public List<Inventory> getInventoryByWarehouse(
            Integer warehouseId) {

        return inventoryRepository
                .findByWarehouseWarehouseId(warehouseId);
    }

    public List<Inventory> getInventoryByProduct(
            Integer productId) {

        return inventoryRepository
                .findByProductProductId(productId);
    }
}