package com.energygrid.user_service.controllers;

import com.energygrid.user_service.repositories.EmployeeRepository;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

}
