package br.com.instivo.salarytime.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DateDifference implements Serializable {


    @Serial
    private static final long serialVersionUID = -4328197123628061386L;

    private int years;
    private int months;
    private int days;
}
