package edu.unimagdalena.inventoryservice.repository;

import edu.unimagdalena.inventoryservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InventoryRepository extends JpaRepository<Inventory, String> {
    Object findAllByIdIn(List<String> id1);
}