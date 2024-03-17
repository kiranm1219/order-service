package com.food.service;


import com.food.enums.Status;
import com.food.model.Order;
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

    @Value("${topic.name.producer}")
    private String topicName;

    @Autowired
    private  KafkaTemplate<String, Order> kafkaTemplate;

    public void createOrder(Order order) {
        order.setStatus(Status.CREATED);
        orderRepository.save(order);
        kafkaTemplate.send(topicName, order);

    }
}
