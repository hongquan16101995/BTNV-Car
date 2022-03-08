package btvn.service.impl;

import btvn.model.Car;
import btvn.repository.ICarRepository;
import btvn.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService implements ICarService {
    @Autowired
    private ICarRepository iCarRepository;

    @Override
    public Iterable<Car> findAll() {
        return iCarRepository.findAll();
    }

    @Override
    public Optional<Car> findOne(Long id) {
        return iCarRepository.findById(id);
    }

    @Override
    public void save(Car car) {
        iCarRepository.save(car);
    }

    @Override
    public void delete(Long id) {
        iCarRepository.deleteById(id);
    }

    @Override
    public Iterable<Car> findByName(String name) {
        return iCarRepository.findAllByNameContaining(name);
    }

    @Override
    public Iterable<Car> findByPriceBetween(Double min, Double max) {
        return iCarRepository.findAllByPriceBetween(min, max);
    }

    @Override
    public Iterable<Car> findByNameAndPriceBetween(String name, Double min, Double max) {
        return iCarRepository.findAllByNameContainingOrPriceBetween(name, min, max);
    }
}
