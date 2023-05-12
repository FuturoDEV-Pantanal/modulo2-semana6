package org.futurodev.employeesapi.repository;

import org.futurodev.employeesapi.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Employee findByRegistration(@Param("registration") Integer registration);

    public Page<Employee> findAllByActiveTrue(Pageable pagination);
}
