package btvn.service.impl;

import btvn.model.CarCompany;
import btvn.repository.ICarCompanyRepository;
import btvn.service.ICarCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarCompanyService implements ICarCompanyService {
    @Autowired
    private ICarCompanyRepository iCarCompanyRepository;

    @Override
    public Iterable<CarCompany> findAll() {
        return iCarCompanyRepository.findAll();
    }

    @Override
    public Optional<CarCompany> findOne(Long id) {
        return iCarCompanyRepository.findById(id);
    }

    @Override
    public void save(CarCompany carCompany) {
        iCarCompanyRepository.save(carCompany);
    }
}
