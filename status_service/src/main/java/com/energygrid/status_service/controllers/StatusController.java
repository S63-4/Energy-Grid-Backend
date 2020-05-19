package com.energygrid.status_service.controllers;

import com.energygrid.status_service.common.dto.StatusDTO;
import com.energygrid.status_service.common.enums.StatusPeriod;
import com.energygrid.status_service.common.events.RegionalEvent;
import com.energygrid.status_service.services.StatusService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

@Controller
public class StatusController {

    private StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = RestURIConstant.getHourStatus, method = RequestMethod.GET)
    public @ResponseBody
    List<StatusDTO> getStatusForPeriod() {
        return null;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/status/all")
    public List<RegionalEvent> getAll() {
        return statusService.getAll();
    }
}
