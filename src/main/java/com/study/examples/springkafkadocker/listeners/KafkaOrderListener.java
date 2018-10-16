package com.study.examples.springkafkadocker.listeners;

import com.study.examples.springkafkadocker.config.KafkaConfig;
import com.study.examples.springkafkadocker.requestModel.Order;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderListener {
    
    public static Logger logger = LoggerFactory.getLogger(KafkaOrderListener.class);

    private KafkaConfig kafkaConfig;

    @Value("${kafka.ordertopic}")
    private String kafkaOrderTopic;


    @Autowired
    KafkaOrderListener(KafkaConfig kafkaConfig) {
        this.kafkaConfig = kafkaConfig;
    }

    @KafkaListener(topics = "kafka_orders", containerFactory = "kafkaListenerContainerFactory")
    public void receiveKafkaOrder(Order order) throws Exception {
        logger.info( " &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&  ");
        logger.info( "         Kafka Listener        ");
        logger.info( " Order id is  {} ", order.getOrderId() );
    }

}
