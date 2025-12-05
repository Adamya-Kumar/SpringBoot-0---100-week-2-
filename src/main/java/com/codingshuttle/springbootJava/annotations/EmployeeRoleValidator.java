package com.codingshuttle.springbootJava.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleVaildation,String> {
    @Override
    public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {
        List<String> role= List.of("ADMIN","admin","USER","user");
        return role.contains(input);
    }
}
