package com.example.demo.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.stream.Collectors;

public class OneOfValidator implements ConstraintValidator<OneOf, Integer> {


    private int[] intValues;

    @Override
    public void initialize(OneOf oneOfConstraintAnnotation) {
        this.intValues = oneOfConstraintAnnotation.value();
    }
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.stream(intValues)
                .boxed()
                .collect(Collectors.toList()).contains(integer);
    }
}
