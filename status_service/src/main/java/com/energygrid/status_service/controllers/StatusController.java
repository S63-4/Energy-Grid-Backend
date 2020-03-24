package com.energygrid.status_service.controllers;

import com.energygrid.common.dto.*;
import com.energygrid.common.enums.StatusPeriod;
import com.energygrid.status_service.repositories.StatusRepository;
import com.energygrid.status_service.services.StatusService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        int id = 1;
        return statusService.getStatusForPeriod(id, statusPeriodEnum, currentDate);
    }
}
