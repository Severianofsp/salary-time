package br.com.instivo.salarytime.repository;

import br.com.instivo.salarytime.domain.Employee;
import br.com.instivo.salarytime.repository.custom.EmployeeCustomRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String>, EmployeeCustomRepository {
}
