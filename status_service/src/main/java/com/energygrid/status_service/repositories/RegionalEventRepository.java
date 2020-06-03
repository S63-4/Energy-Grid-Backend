package com.energygrid.status_service.repositories;

import com.energygrid.status_service.common.events.RegionalEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface RegionalEventRepository extends CrudRepository<RegionalEvent, Long> {
    public RegionalEvent findByLocalDateTime(LocalDateTime localDateTime);
}
