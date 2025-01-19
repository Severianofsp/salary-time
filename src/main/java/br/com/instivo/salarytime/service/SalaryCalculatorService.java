package br.com.instivo.salarytime.service;

import java.math.BigDecimal;

public interface SalaryCalculatorService {

    BigDecimal calculatePercentage(BigDecimal grossSalary);
}
