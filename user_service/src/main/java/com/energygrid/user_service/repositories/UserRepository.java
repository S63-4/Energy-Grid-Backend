package com.energygrid.user_service.repositories;

import com.energygrid.common.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserById(Long id);
    User findUserByEmail(String email);
    User findUserByCustomerCode(String customerCode);


    Boolean existsByEmail(String email);
    Boolean existsByCustomerCode(String customerCode);
}
