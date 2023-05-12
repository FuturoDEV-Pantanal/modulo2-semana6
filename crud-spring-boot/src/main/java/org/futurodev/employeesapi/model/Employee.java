package org.futurodev.employeesapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.futurodev.employeesapi.model.transport.EmployeeDTO;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Integer registration;

    @Column(nullable = false)
    private String name;
    private String email;
    private String phone;
    private Double wage;

    @Embedded
    private Address address;
    private boolean active;

    public Employee(EmployeeDTO employeeDTO) {
        this.registration = employeeDTO.registration();
        this.name = employeeDTO.name();
        this.email = employeeDTO.email();
        this.phone = employeeDTO.phone();
        this.wage = employeeDTO.wage();
        this.address = new Address(employeeDTO.address());
        this.active = true;
    }
}
