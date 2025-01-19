package br.com.instivo.salarytime;

import br.com.instivo.salarytime.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SalaryTimeApplication implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(SalaryTimeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
