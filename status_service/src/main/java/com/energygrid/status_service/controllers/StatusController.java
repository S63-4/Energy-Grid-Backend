package com.energygrid.status_service.controllers;

import com.energygrid.status_service.common.dto.CustomerStatusDTO;
import com.energygrid.status_service.common.dto.StatusDTO;
import com.energygrid.status_service.common.events.RegionalEvent;
import com.energygrid.status_service.services.StatusService;
import com.google.gson.Gson;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
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
    String getStatusForHourPeriod(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(value = "endDate", defaultValue = "#{T(java.time.LocalDateTime).now()}") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        List<StatusDTO> events = statusService.getRegionalEventByDates(startDate, endDate);

        Gson gson = new Gson();
        String result = gson.toJson(events);

        return result;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = RestURIConstant.getGetHourStatusCustomer, method = RequestMethod.GET)
    public @ResponseBody
    String getCustomerStatusForHourPeriod(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(value = "endDate", defaultValue = "#{T(java.time.LocalDateTime).now()}") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailAddress = (String) auth.getPrincipal();
        String zipCode = statusService.getCustumerZipCodeByEmail(emailAddress);
        List<CustomerStatusDTO> events = statusService.getCustomerStatus(zipCode, startDate, endDate);
        Gson gson = new Gson();
        String result = gson.toJson(events);
        return result;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/status/all")
    public List<RegionalEvent> getAll() {
        return statusService.getAll();
    }
}
