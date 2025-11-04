package com.syshco.ai.tools.employee;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final List<Employee> employees = List.of(
            new Employee(1, "Alice", "Engineering", "alice@company.com"),
            new Employee(2, "Bob", "Marketing", "bob@company.com"),
            new Employee(3, "Charlie", "HR", "charlie@company.com")
    );

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employees.stream()
                .filter(emp -> emp.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/search")
    public Employee getEmployeeByName(@RequestParam String name) {
        return employees.stream()
                .filter(emp -> emp.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
