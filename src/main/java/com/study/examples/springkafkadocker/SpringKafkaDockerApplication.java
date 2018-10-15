package com.study.examples.springkafkadocker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.study.examples.springkafkadocker.requestModel.Order;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Type;
import java.time.LocalDate;

@SpringBootApplication
public class SpringKafkaDockerApplication {

    public static void main(String[] args) {

  /*       final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();

        //Gson gson = new Gson();
        final Order desrializedOrder = gson.fromJson("{\n" +
                "\t\"orderPrice\": 55.7,\n" +
                "\t\"items\": [{\n" +
                "\t\t\"itemId\": \"xxID12\",\n" +
                "\t\t\"itemDescription\": \"An utility hammer needed for basic houselhold needs\",\n" +
                "\t\t\"manufacturingDate\": \"2017-11-29\",\n" +
                "\t\t\"itemPrice\": \"10.0\"\n" +
                "\t}]\n" +
                "\n" +
                "}",  Order.class);

        System.out.print(" Hello ");*/

        SpringApplication.run(SpringKafkaDockerApplication.class, args);
    }
}
