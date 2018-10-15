package com.study.examples.springkafkadocker.service;

public interface BaseService<T> {

    String generateId();

    T persist(T t);

    T fetch(String s);
}
