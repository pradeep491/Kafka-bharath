package com.test.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class OrderConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.setProperty("group.id", "OrderGroup");

        KafkaConsumer<String, Integer> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("OrderTopic"));
        ConsumerRecords<String, Integer> records = consumer.poll(Duration.ofSeconds(20));
        for (ConsumerRecord<String, Integer> consumerRecord : records) {
            System.out.println(consumerRecord.key());
            System.out.println(consumerRecord.value());
        }
        consumer.close();
    }
}
