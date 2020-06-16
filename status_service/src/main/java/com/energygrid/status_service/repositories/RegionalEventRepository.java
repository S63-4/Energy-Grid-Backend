package com.energygrid.status_service.repositories;

import com.energygrid.status_service.common.events.RegionalEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.synth.Region;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RegionalEventRepository extends CrudRepository<RegionalEvent, Long> {
    public RegionalEvent findByLocalDateTime(LocalDateTime localDateTime);

    List<RegionalEvent> findByLocalDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
}
