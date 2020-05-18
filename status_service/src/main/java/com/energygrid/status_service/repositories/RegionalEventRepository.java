package com.energygrid.status_service.repositories;

import com.energygrid.status_service.common.events.RegionalEvent;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface RegionalEventRepository extends CrudRepository<RegionalEvent, Long> {
    public RegionalEvent findByLocalDateTime(LocalDateTime localDateTime);
}
