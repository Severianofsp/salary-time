package br.com.instivo.salarytime.builder;

import br.com.instivo.salarytime.domain.Employee;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class EmployeeBuilder {

    private Employee employee;

    public static EmployeeBuilder buildEmployeeDefault() {
        EmployeeBuilder builder = new EmployeeBuilder();
        builder.employee = Employee.builder()
                .id(new ObjectId("6ccaafd2e95b90f6baaa3f9e"))
                .name("John")
                .grossSalary(BigDecimal.valueOf(10000))
                .admissionDate(LocalDate.now())
                .build();
        return builder;
    }

    public EmployeeBuilder withoutId() {
        this.employee.setId(null);
        return this;
    }

    public Page<Employee> toPage() {
        return new PageImpl<>(List.of(employee));
    }

    public Employee build() {
        return employee;
    }
}
