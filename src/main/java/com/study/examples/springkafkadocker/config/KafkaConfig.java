package com.study.examples.springkafkadocker.config;

import com.study.examples.springkafkadocker.common.serde.OrderDeserializer;
import com.study.examples.springkafkadocker.common.serde.OrderSerializer;
import com.study.examples.springkafkadocker.requestModel.Order;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Value("${kafka.ordertopic}")
    private String orderTopic;

    @Value("${kafka.broker}")
    private String bootstrapServer;

    @Value("${kafka.consumer.groupid}")
    private String orderConsumerGroupId;


    private Map<String, Object> producerConfigs() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, OrderSerializer.class);
        return configs;
    }

    @Bean
    public ProducerFactory<Integer, Order> createOrderProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<Integer, Order> createKafkaTemplate() {
        return new KafkaTemplate<Integer, Order>(createOrderProducerFactory());
    }

    private Map<String, Object> consumerConfigs() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, orderConsumerGroupId);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, OrderDeserializer.class);

        return configs;
    }

    @Bean
    public ConsumerFactory<Integer, Order> createOrderConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new IntegerDeserializer(),
                new OrderDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Integer, Order> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, Order> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(createOrderConsumerFactory());
        return factory;
    }

    public String getOrderTopic() {
        return orderTopic;
    }
}