package br.com.instivo.salarytime;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@Log4j2
@SpringBootApplication
@RequiredArgsConstructor
public class SalaryTimeApplication implements CommandLineRunner {

    private final Environment env;

    public static void main(String[] args) {
        SpringApplication.run(SalaryTimeApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("--------------------------------------------");
        log.info("--------------------------------------------");

        log.info("SalaryTimeApplication started");
        log.info("Environment: {}", env.getProperty("ENV_DEVELOPMENT"));

        log.info("--------------------------------------------");
        log.info("--------------------------------------------");
    }
}
