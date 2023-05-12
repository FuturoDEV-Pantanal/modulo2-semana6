package org.futurodev.employeesapi.controller;

import org.futurodev.employeesapi.model.transport.EmployeeDTO;
import org.futurodev.employeesapi.services.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> register(@RequestBody EmployeeDTO body) {
        EmployeeDTO response = this.employeeService.register(body);
        return ResponseEntity.created(URI.create("/employee")).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeDTO>> list(@PageableDefault(size = 10, page = 0, sort = "name") Pageable pagination) {
        Page<EmployeeDTO> response = this.employeeService.list(pagination);
        if (!response.hasContent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody EmployeeDTO body) {
        this.employeeService.update(body);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{registration}")
    public ResponseEntity<Void> deactivate(@PathVariable("registration") Integer registration) {
        this.employeeService.deactivate(registration);
        return ResponseEntity.ok().build();
    }

}
