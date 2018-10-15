package com.study.examples.springkafkadocker.restcontroller;

import com.study.examples.springkafkadocker.repository.OrdersRepository;
import com.study.examples.springkafkadocker.requestModel.Order;
import com.study.examples.springkafkadocker.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> completeOrder(@Valid @RequestBody Order order){
        @Valid Order persistedOrder = orderService.persist(order);
        return new ResponseEntity<>(persistedOrder.getOrderId(), HttpStatus.CREATED);
    }

}
