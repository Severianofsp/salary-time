package br.com.instivo.salarytime.service.impl;

import br.com.instivo.salarytime.service.SalaryCalculatorService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class SalaryCalculatorServiceImpl implements SalaryCalculatorService {

    private static final BigDecimal PERCENTAGE = BigDecimal.valueOf(0.35);

    @Override
    public BigDecimal calculatePercentage(BigDecimal grossSalary) {
        if (grossSalary == null || grossSalary.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Gross salary must be non-null and non-negative.");
        }
        return grossSalary.multiply(PERCENTAGE).setScale(2, RoundingMode.HALF_UP);
    }
}
