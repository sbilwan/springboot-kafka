package com.study.examples.springkafkadocker.restcontroller;

import com.study.examples.springkafkadocker.requestModel.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> completeOrder(@Valid @RequestBody Order order){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
