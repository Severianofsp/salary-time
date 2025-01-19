package br.com.instivo.salarytime.repository;

import br.com.instivo.salarytime.domain.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, UUID> {
}
