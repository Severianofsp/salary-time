package br.com.instivo.salarytime.service;

import br.com.instivo.salarytime.service.impl.SalaryCalculatorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class SalaryCalculatorServiceTest {

    @InjectMocks
    private SalaryCalculatorServiceImpl salaryService;

    @Test
    void calculatePercentage_shouldReturnCorrectPercentage() {
        // Data
        BigDecimal grossSalary = BigDecimal.valueOf(1000);
        BigDecimal expected = BigDecimal.valueOf(350.0);

        // Action
        BigDecimal result = salaryService.calculatePercentage(grossSalary);

        // Result
        assertEquals(expected.doubleValue(), result.doubleValue());
    }

    @Test
    void calculatePercentage_shouldThrowExceptionWhenGrossSalaryIsNull() {
        // Action and Result
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> salaryService.calculatePercentage(null));

        assertEquals("Gross salary must be non-null and non-negative.", exception.getMessage());
    }

    @Test
    void calculatePercentage_shouldThrowExceptionWhenGrossSalaryIsNegative() {
        // Data
        BigDecimal negativeSalary = BigDecimal.valueOf(-1000);

        // Action and Result
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> salaryService.calculatePercentage(negativeSalary));

        assertEquals("Gross salary must be non-null and non-negative.", exception.getMessage());
    }

    @Test
    void calculatePercentage_shouldReturnZeroWhenGrossSalaryIsZero() {
        // Data
        BigDecimal grossSalary = BigDecimal.ZERO;
        BigDecimal expected = BigDecimal.ZERO;

        // Action
        BigDecimal result = salaryService.calculatePercentage(grossSalary);

        // Result
        assertEquals(expected.doubleValue(), result.doubleValue());
    }
}
