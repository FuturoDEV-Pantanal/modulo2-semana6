package org.futurodev.employeesapi.services;

import org.futurodev.employeesapi.model.Employee;
import org.futurodev.employeesapi.model.transport.EmployeeDTO;
import org.futurodev.employeesapi.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public EmployeeDTO register(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(employeeDTO);
        this.employeeRepository.save(employee);

        return employeeDTO;
    }

    public Page<EmployeeDTO> list(Pageable pagination) {
        return this.employeeRepository.findAllByActiveTrue(pagination).map(EmployeeDTO::new);
    }

    @Transactional
    public void update(EmployeeDTO body) {
        Employee employee = this.employeeRepository.findByRegistration(body.registration());
        if (body.name() != null) {
            employee.setName(body.name());
        }

        this.employeeRepository.save(employee);
    }

    @Transactional
    public void deactivate(Integer registration) {
        Employee employee = this.employeeRepository.findByRegistration(registration);
        employee.setActive(false);
        this.employeeRepository.save(employee);
    }
}
