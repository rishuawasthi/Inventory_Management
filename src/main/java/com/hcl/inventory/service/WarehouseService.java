package com.hcl.inventory.service;

import com.hcl.inventory.entity.Warehouse;
import com.hcl.inventory.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse getWarehouseById(Integer warehouseId) {
        return warehouseRepository.findById(warehouseId).orElse(null);
    }

    public Warehouse addWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Warehouse updateWarehouse(Integer warehouseId, Warehouse warehouse) {

        Warehouse existingWarehouse =
                warehouseRepository.findById(warehouseId).orElse(null);

        if (existingWarehouse == null) {
            return null;
        }

        existingWarehouse.setWarehouseName(warehouse.getWarehouseName());
        existingWarehouse.setWarehouseLocation(warehouse.getWarehouseLocation());

        return warehouseRepository.save(existingWarehouse);
    }

    public boolean deleteWarehouse(Integer warehouseId) {

        if (!warehouseRepository.existsById(warehouseId)) {
            return false;
        }

        warehouseRepository.deleteById(warehouseId);
        return true;
    }
}