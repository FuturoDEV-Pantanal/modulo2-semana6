package org.futurodev.employeesapi.model.transport;

import org.futurodev.employeesapi.model.Employee;

public record EmployeeDTO(Integer registration, String name, String email, String phone, Double wage,
                          AddressDTO address) {

    public EmployeeDTO(Employee employee) {
        this(employee.getRegistration(), employee.getName(), employee.getEmail(), employee.getPhone(), employee.getWage(), new AddressDTO(employee.getAddress()));
    }
}
