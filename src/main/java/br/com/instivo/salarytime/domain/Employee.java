package br.com.instivo.salarytime.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "employee")
public class Employee implements Serializable {

    @Serial
    private static final long serialVersionUID = -5650845437603207433L;

    @Id
    private ObjectId id;
    private LocalDate admissionDate;
    private BigDecimal grossSalary;
}
