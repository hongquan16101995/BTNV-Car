package btvn.service;

import btvn.model.CarCompany;

import java.util.Optional;

public interface ICarCompanyService {
    Iterable<CarCompany> findAll();

    Optional<CarCompany> findOne(Long id);

    void save(CarCompany carCompany);
}
