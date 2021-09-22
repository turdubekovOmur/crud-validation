package com.web.crudvalidation.controller;

import com.web.crudvalidation.entity.Employee;
import com.web.crudvalidation.exception.ResourceNotFoundException;
import com.web.crudvalidation.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepository repository;

    @GetMapping("/employees")
    public List<Employee> getAll(){
        return repository.findAll();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found by id"));
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@Valid  @RequestBody Employee employee){
        return repository.save(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee>  updateEmployee(@PathVariable Long id,
                                                    @Valid @RequestBody Employee employeeDetails)throws ResourceNotFoundException{
        Employee employee = new Employee();
        employee = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());
        final Employee updatedEmployee = repository.save(employee);

        return ResponseEntity.ok().body(updatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable Long id) throws ResourceNotFoundException {
        Employee employee = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        repository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }
}
