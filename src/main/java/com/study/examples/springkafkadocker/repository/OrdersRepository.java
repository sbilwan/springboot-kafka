package com.study.examples.springkafkadocker.repository;

import com.study.examples.springkafkadocker.requestModel.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Order, String> {


}
