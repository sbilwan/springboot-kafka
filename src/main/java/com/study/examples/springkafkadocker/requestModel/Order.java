package com.study.examples.springkafkadocker.requestModel;

import java.math.BigDecimal;
import java.util.List;

public class Order {

    private String orderId;

    private BigDecimal orderPrice;

    private List<String> items;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
