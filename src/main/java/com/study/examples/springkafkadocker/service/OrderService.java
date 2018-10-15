package com.study.examples.springkafkadocker.service;

import com.study.examples.springkafkadocker.requestModel.Order;

import java.util.concurrent.Future;

public interface OrderService extends BaseService<Order> {

    Future send(Order order) ;

}
