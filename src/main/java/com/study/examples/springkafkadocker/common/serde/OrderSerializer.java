package com.study.examples.springkafkadocker.common.serde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.study.examples.springkafkadocker.requestModel.Order;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class OrderSerializer implements Serializer<Order> {


    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Order data) {
        byte [] orderAsBytes = null;
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            orderAsBytes = objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
           throw new RuntimeException(e);
        }
        return orderAsBytes;
    }

    @Override
    public void close() {

    }
}
