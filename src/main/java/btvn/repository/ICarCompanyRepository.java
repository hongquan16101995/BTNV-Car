package btvn.repository;

import btvn.model.CarCompany;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarCompanyRepository extends CrudRepository<CarCompany, Long> {
}
