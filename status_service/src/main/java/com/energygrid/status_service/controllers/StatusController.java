package com.energygrid.status_service.controllers;

import com.energygrid.status_service.common.dto.StatusDTO;
import com.energygrid.status_service.common.enums.StatusPeriod;
import com.energygrid.status_service.repositories.StatusRepository;
import com.energygrid.status_service.services.StatusService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

@Controller
public class StatusController {

    private final StatusService statusService;
    private final StatusRepository statusRepository;

    public StatusController(StatusService statusService, StatusRepository statusRepository) {
        this.statusService = statusService;
        this.statusRepository = statusRepository;
    }

    @RequestMapping(value = RestURIConstant.getStatus, method = RequestMethod.GET)
    public @ResponseBody
    List<StatusDTO> getStatusForPeriod(@RequestParam("statusPeriod") String statusPeriod, @RequestParam("currentDate") String unixDateTime) {
        var statusPeriodEnum = StatusPeriod.values()[Integer.parseInt(statusPeriod)];
        var unixInteger = Long.parseLong(unixDateTime);
        var currentDate = Instant.ofEpochMilli(unixInteger).atZone(ZoneId.systemDefault()).toLocalDate();
        return statusService.getStatusForPeriod(statusPeriodEnum, currentDate);
    }
}
