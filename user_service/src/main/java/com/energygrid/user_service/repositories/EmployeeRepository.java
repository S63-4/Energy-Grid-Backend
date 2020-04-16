package com.energygrid.user_service.repositories;


import com.energygrid.user_service.common.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    //Employee findEmployeeByEmployeeNumber(String code) ;

}
