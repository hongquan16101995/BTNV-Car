package btvn.repository;

import btvn.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarRepository extends CrudRepository<Car, Long> {
    Iterable<Car> findAllByNameContaining(String name);

    Iterable<Car> findAllByPriceBetween(Double minPrice, Double maxPrice);

    Iterable<Car> findAllByNameContainingOrPriceBetween(String name, Double minPrice, Double maxPrice);
}
