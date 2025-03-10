package edu.unimagdalena.orderservice.service;

import edu.unimagdalena.orderservice.entity.Order;
import edu.unimagdalena.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Flux<Order> getAllOrders() {
        return Flux.defer(() -> Flux.fromIterable(orderRepository.findAll()))
                .subscribeOn(Schedulers.boundedElastic());
    }
    public Mono<Order> getOrderById(String id){
        return Mono.defer(() -> Mono.justOrEmpty(orderRepository.findById(id)))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Order> createOrder(Order order){
        order.setId(UUID.randomUUID().toString());
        return Mono.defer(() -> Mono.just(orderRepository.save(order)))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Order> updateOrder(String id, Order order){
        return Mono.defer(() -> {
            if(orderRepository.existsById(id)){
                order.setId(id);
                return Mono.just(orderRepository.save(order));
            }
            return Mono.empty();
        }).subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Void> deleteOrder(String id){
        return Mono.defer(() -> {
            if(orderRepository.existsById(id)){
                orderRepository.deleteById(id);
                return Mono.empty();
            }
            return Mono.error(new NoSuchElementException("Order not fount"));
        }).subscribeOn(Schedulers.boundedElastic()).then();
    }
}
