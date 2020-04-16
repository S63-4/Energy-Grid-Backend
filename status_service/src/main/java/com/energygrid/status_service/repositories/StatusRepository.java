package com.energygrid.status_service.repositories;

import com.energygrid.status_service.common.models.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StatusRepository extends CrudRepository<Status, Long> {
    Status findStatusById(Long id);

    @Query("Select s from Status s WHERE s.date >= :startDate AND s.date <= :endDate AND s.userId = :userId")
    List<Status> findByDateandUser(@Param("startDate") Date date1, @Param("endDate") Date date2, @Param("userId") Long userId);
}
