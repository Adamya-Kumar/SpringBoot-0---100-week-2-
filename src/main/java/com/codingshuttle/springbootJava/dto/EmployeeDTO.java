package com.codingshuttle.springbootJava.dto;
import com.codingshuttle.springbootJava.annotations.EmployeeRoleVaildation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Name field should not be blank")
    @Size(min=3,max=10,message = "Number character of name => 3 and 10 <= ")
    private String name;
    @NotBlank(message = "Email of the employee cannot not blank")
    @Email(message = "Email should be valid email")
    private String email;
    @NotNull(message = "Age cannot be Null")
    @Max(value=80,message = "Age of Employee cannot greater than 80")
    @Min(value=18,message = "Age of Employee cannot less than 18")
    private Integer age;
    @NotBlank(message = "Role of the employee cannot not blank")
    //@Pattern(regexp = "^(ADMIN|USER|admin|user)$",message = "Role of employee can be USER or ADMIN")
    @EmployeeRoleVaildation
    private String role;
    private LocalDate dateOfJoining;

}
