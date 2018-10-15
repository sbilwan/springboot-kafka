package com.study.examples.springkafkadocker.service.impl;

import com.study.examples.springkafkadocker.config.KafkaConfig;
import com.study.examples.springkafkadocker.repository.OrdersRepository;
import com.study.examples.springkafkadocker.requestModel.Order;
import com.study.examples.springkafkadocker.service.OrderService;
import org.apache.kafka.connect.errors.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.Future;

@Component
public class OrderServiceImpl implements OrderService {

    private OrdersRepository ordersRepository;

    private KafkaTemplate<Integer, Order> orderKafkaTemplate;

    private KafkaConfig kafkaConfig;

    @Autowired
    OrderServiceImpl(OrdersRepository ordersRepository, KafkaConfig config, KafkaTemplate orderKafkaTemplate){
        this.ordersRepository = ordersRepository;
        this.kafkaConfig = config;
        this.orderKafkaTemplate = orderKafkaTemplate;
    }

    @Override
    public String generateId() {
        // OrderId takes detail from userid. For now consider User1.
        StringBuilder idBuilder = new StringBuilder();
        idBuilder.append("User1");
        idBuilder.append(LocalDateTime.now());
        return idBuilder.toString();
    }

    @Override
    public Order persist(Order order) {
        Order persistedOrder = ordersRepository.saveAndFlush(order);
        send(persistedOrder);
        return persistedOrder;
    }

    @Override
    public Order fetch(String orderId) {
       return ordersRepository.findById(orderId)
               .orElseThrow( () -> new NotFoundException("Order with id not found" + orderId));
    }

    @Override
    public Future send(Order order) {
        return orderKafkaTemplate.send(kafkaConfig.getOrderTopic(), order);
    }
}
