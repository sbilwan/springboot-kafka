package com.study.examples.springkafkadocker.common.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.study.examples.springkafkadocker.requestModel.Order;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.IOException;
import java.util.Map;

public class OrderDeserializer implements Deserializer<Order> {


    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Order deserialize(String topic, byte[] data) {

        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
        final StringDeserializer deserializer = new StringDeserializer();
        final String deStringOrder = deserializer.deserialize(topic, data);
        Order deOrder = null;
        try {
            deOrder = objectMapper.readValue(deStringOrder, Order.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return deOrder;
    }

    @Override
    public void close() {

    }
}
