package com.energygrid.user_service.repositories;


import com.energygrid.user_service.common.models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findCustomerByCustomerCode(String code);

    boolean existsByCustomerCode(String customerCode);

    boolean existsByEmail(String email);
}
