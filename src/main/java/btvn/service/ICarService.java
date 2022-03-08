package btvn.service;

import btvn.model.Car;

import java.util.Optional;

public interface ICarService {
    Iterable<Car> findAll();

    Optional<Car> findOne(Long id);

    void save(Car car);

    void delete(Long id);

    Iterable<Car> findByName(String name);

    Iterable<Car> findByPriceBetween(Double min, Double max);

    Iterable<Car> findByNameAndPriceBetween(String name, Double min, Double max);
}
