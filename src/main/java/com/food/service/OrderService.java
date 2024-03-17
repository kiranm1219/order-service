package com.food.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.food.enums.Status;
import com.food.model.Order;
import com.food.producer.OrderCreatedEventProducer;
import com.food.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    OrderCreatedEventProducer createdEventProducer;



    @Autowired
    private  KafkaTemplate<String, Order> kafkaTemplate;

    public void createOrder(Order order) {
        order.setStatus(Status.CREATED);
        orderRepository.save(order);
        createdEventProducer.produce(order);
    }
}
