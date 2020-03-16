package com.energygrid.common.repositories;

import com.energygrid.common.models.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StatusRepository extends CrudRepository<Status, Long> {
  Status findStatusById(Long id);
    List<Status> findByDateBetween(Date date1, Date date2);
}
