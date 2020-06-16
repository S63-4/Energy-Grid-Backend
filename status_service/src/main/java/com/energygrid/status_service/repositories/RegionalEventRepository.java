package com.energygrid.status_service.repositories;

import com.energygrid.status_service.common.events.RegionalEvent;
import com.energygrid.status_service.common.models.ConsumerWithDateTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.synth.Region;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RegionalEventRepository extends CrudRepository<RegionalEvent, Long> {
    public RegionalEvent findByLocalDateTime(LocalDateTime localDateTime);

    List<RegionalEvent> findByLocalDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
    @Query(
            value = "   SELECT c.*, e.date_time as localdatetime FROM consumer c\n" +
                    "   INNER JOIN consumer_group cg\n" +
                    "   ON c.consumer_id = cg.id\n" +
                    "   INNER JOIN event e\n" +
                    "   ON cg.group_id = e.consumption_id\n" +
                    "                        WHERE c.name = ?1\n" +
                    "                        AND c.consumer_id IN (\n" +
                    "                        SELECT id FROM consumer_group\n" +
                    "                        WHERE group_id IN (\n" +
                    "                        SELECT id FROM consumption\n" +
                    "                        WHERE id IN (\n" +
                    "                        SELECT consumption_id FROM event\n" +
                    "                    WHERE date_time BETWEEN ?2 AND ?3 )))",
            nativeQuery = true
    )
    List<ConsumerWithDateTime> findConsumersByZipcodeBetween(String zipcode, LocalDateTime startDate, LocalDateTime endDate);
}
