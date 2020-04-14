package com.energygrid.user_service.repositories;

import com.energygrid.user_service.common.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserById(Long id);

    User findUserByEmail(String email);
    Long findIdByEmail(String email);


    Boolean existsByEmail(String email);
}
