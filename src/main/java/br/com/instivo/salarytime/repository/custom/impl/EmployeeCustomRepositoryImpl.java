package br.com.instivo.salarytime.repository.custom.impl;

import br.com.instivo.salarytime.domain.Employee;
import br.com.instivo.salarytime.model.dto.EmployeeFilterDTO;
import br.com.instivo.salarytime.repository.custom.EmployeeCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Page<Employee> findAllWithFilters(EmployeeFilterDTO filter, Pageable pageable) {
        Query query = new Query();

        List<Criteria> criteriaList = new ArrayList<>();

        if (filter.getName() != null && !filter.getName().isBlank()) {
            criteriaList.add(Criteria.where("name").regex(filter.getName(), "i"));
        }
        if (filter.getStartAdmissionPeriodDate() != null) {
            criteriaList.add(Criteria.where("admissionDate").gte(filter.getStartAdmissionPeriodDate()));
        }
        if (filter.getEndAdmissionPeriodDate() != null) {
            criteriaList.add(Criteria.where("admissionDate").lte(filter.getEndAdmissionPeriodDate()));
        }
        if (filter.getGrossSalaryGreaterThan() != null) {
            criteriaList.add(Criteria.where("grossSalary").gte(filter.getGrossSalaryGreaterThan()));
        }
        if (filter.getGrossSalaryLessThan() != null) {
            criteriaList.add(Criteria.where("grossSalary").lte(filter.getGrossSalaryLessThan()));
        }

        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        }

        query.with(pageable);

        List<Employee> employees = mongoTemplate.find(query, Employee.class);

        long count = mongoTemplate.count(query, Employee.class);

        return new PageImpl<>(employees, pageable, count);
    }
}
