package edu.unimagdalena.inventoryservice.service;

import edu.unimagdalena.inventoryservice.entity.Inventory;
import edu.unimagdalena.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public Flux<Inventory> getAllInventoryItems() {
        return Flux.defer(() -> Flux.fromIterable(inventoryRepository.findAll()))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Inventory> getInventoryItemById(String id) {
        return Mono.defer(() -> Mono.justOrEmpty(inventoryRepository.findById(id)))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Inventory> createInventoryItem(Inventory inventory) {
        inventory.setId(UUID.randomUUID().toString());
        return Mono.defer(() -> Mono.just(inventoryRepository.save(inventory)))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Inventory> updateInventoryItem(String id, Inventory inventory) {
        return Mono.defer(() -> {
            if (inventoryRepository.existsById(id)) {
                inventory.setId(id);
                return Mono.just(inventoryRepository.save(inventory));
            }
            return Mono.empty();
        }).subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Void> deleteInventoryItem(String id) {
        return Mono.defer(() -> {
            inventoryRepository.deleteById(id);
            return Mono.empty();
        }).subscribeOn(Schedulers.boundedElastic()).then();
    }


}