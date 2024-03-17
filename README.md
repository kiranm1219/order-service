Order service

Add this  sequence diagran on sequencediagram.org

sequenceDiagram
    participant User
    participant OrderService
    participant Kafka
    participant PaymentService
    participant RestaurantService
    User->>OrderService: Place Order
    OrderService->>Kafka: Publish OrderCreated event
    Kafka->>PaymentService: Consume OrderCreated event
    PaymentService->>Kafka: Publish PaymentProcessed event
    Kafka->>RestaurantService: Consume PaymentProcessed event
    alt Order Confirmed
        RestaurantService->>Kafka: Publish OrderConfirmed event
    else Order Rejected
        RestaurantService->>Kafka: Publish OrderRejected event
    end
