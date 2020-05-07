package com.energygrid.status_service.services;

import com.energygrid.status_service.common.dto.StatusDTO;
import com.energygrid.status_service.common.enums.StatusPeriod;
import com.energygrid.status_service.common.models.Status;
import com.energygrid.status_service.repositories.StatusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class StatusService {


    @Autowired
    private RestTemplate restTemplate;
    private final StatusRepository statusRepository;
    private final ModelMapper modelMapper;

    public StatusService(StatusRepository statusRepository, ModelMapper modelMapper) {
        this.statusRepository = statusRepository;
        this.modelMapper = modelMapper;
    }

    private long getCurrentUserId() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String email = (String) auth.getPrincipal();
        Long id = restTemplate.getForObject("http://user-service/UserController/id?email=" + email, Long.class);
        return id;
    }

    public void saveStatus() {

    }

    public List<StatusDTO> getStatusForPeriod(StatusPeriod statusPeriod, LocalDate currentDate) {
        var id = getCurrentUserId();
        try {
            LocalDate beginDate;
            LocalDate endDate;
            switch (statusPeriod) {
                case YEAR:
                    beginDate = LocalDate.now().minusMonths(12).withDayOfMonth(1);
                    endDate = currentDate.withDayOfMonth(currentDate.lengthOfMonth());
                    break;
                case THREEMONTHS:
                    beginDate = LocalDate.now().minusMonths(3).withDayOfMonth(1);
                    endDate = currentDate.withDayOfMonth(currentDate.lengthOfMonth());
                    break;
     /*           case MONTH:
                    beginDate = LocalDate.now().minusMonths(1).withDayOfMonth(1);
                    break;
                case WEEK:
                    beginDate = LocalDate.now().minusWeeks(1);
                    break;*/
                default:
                    beginDate = null;
                    endDate = null;
                    // code block
            }
      /*      User user = userRepository.findUserById(id);
            Set<StatusDTO> statusSet = new HashSet<>();
            for (Status status : user.getStatus()) {
                statusSet.add(modelMapper.map(status, StatusDTO.class));
            }*/
            if (beginDate != null) {
                return sortStatusForPeriod(beginDate, endDate, statusPeriod, id);
            }


            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    private List<StatusDTO> sortStatusForPeriod(LocalDate beginDate, LocalDate endDate, StatusPeriod statusPeriod, long id) {

        var filtered = statusRepository.findByDateandUser(java.sql.Date.valueOf(beginDate), java.sql.Date.valueOf(endDate), id);
        List<StatusDTO> statusDTOS = new ArrayList<>();
        List<StatusDTO> result = new ArrayList<>();
        for (Status status : filtered) {
            statusDTOS.add(modelMapper.map(status, StatusDTO.class));
        }
        switch (statusPeriod) {
            case YEAR:
                StatusDTO january = new StatusDTO(getMonthDisplayName(Month.JANUARY));
                StatusDTO february = new StatusDTO(getMonthDisplayName(Month.FEBRUARY));
                StatusDTO march = new StatusDTO(getMonthDisplayName(Month.MARCH));
                StatusDTO april = new StatusDTO(getMonthDisplayName(Month.APRIL));
                StatusDTO may = new StatusDTO(getMonthDisplayName(Month.MAY));
                StatusDTO june = new StatusDTO(getMonthDisplayName(Month.JUNE));
                StatusDTO july = new StatusDTO(getMonthDisplayName(Month.JULY));
                StatusDTO august = new StatusDTO(getMonthDisplayName(Month.AUGUST));
                StatusDTO september = new StatusDTO(getMonthDisplayName(Month.SEPTEMBER));
                StatusDTO october = new StatusDTO(getMonthDisplayName(Month.OCTOBER));
                StatusDTO november = new StatusDTO(getMonthDisplayName(Month.NOVEMBER));
                StatusDTO december = new StatusDTO(getMonthDisplayName(Month.DECEMBER));
                for (StatusDTO statusDTO : statusDTOS) {
                    switch (statusDTO.getLocalDate().getMonthValue()) {
                        case 1:
                            january.addConsumption(statusDTO.getConsumption());
                            january.addProduction(statusDTO.getProduction());
                            break;
                        case 2:
                            february.addConsumption(statusDTO.getConsumption());
                            february.addProduction(statusDTO.getProduction());
                            break;
                        case 3:
                            march.addConsumption(statusDTO.getConsumption());
                            march.addProduction(statusDTO.getProduction());
                            break;
                        case 4:
                            april.addConsumption(statusDTO.getConsumption());
                            april.addProduction(statusDTO.getProduction());
                            break;
                        case 5:
                            may.addConsumption(statusDTO.getConsumption());
                            may.addProduction(statusDTO.getProduction());
                            break;
                        case 6:
                            june.addConsumption(statusDTO.getConsumption());
                            june.addProduction(statusDTO.getProduction());
                            break;
                        case 7:
                            july.addConsumption(statusDTO.getConsumption());
                            july.addProduction(statusDTO.getProduction());
                            break;
                        case 8:
                            august.addConsumption(statusDTO.getConsumption());
                            august.addProduction(statusDTO.getProduction());
                            break;
                        case 9:
                            september.addConsumption(statusDTO.getConsumption());
                            september.addProduction(statusDTO.getProduction());
                            break;
                        case 10:
                            october.addConsumption(statusDTO.getConsumption());
                            october.addProduction(statusDTO.getProduction());
                            break;
                        case 11:
                            november.addConsumption(statusDTO.getConsumption());
                            november.addProduction(statusDTO.getProduction());
                            break;
                        case 12:
                            december.addConsumption(statusDTO.getConsumption());
                            december.addProduction(statusDTO.getProduction());
                            break;
                    }
                }
                result.addAll(Arrays.asList(january, february, march, april, may, june, july, august, september, october, november, december));
                break;
            case THREEMONTHS:
                StatusDTO currentMonth = new StatusDTO(getMonthDisplayName(LocalDate.now().getMonth()));
                StatusDTO lastMonth = new StatusDTO(getMonthDisplayName(LocalDate.now().minusMonths(1).getMonth()));
                StatusDTO lastLastMonth = new StatusDTO(getMonthDisplayName(LocalDate.now().minusMonths(2).getMonth()));
                for (StatusDTO statusDTO : statusDTOS) {
                    var month = statusDTO.getLocalDate().getMonthValue();
                    if (month == LocalDate.now().getMonthValue()) {
                        currentMonth.addProduction(statusDTO.getProduction());
                        currentMonth.addConsumption(statusDTO.getConsumption());
                    } else if (month == LocalDate.now().getMonthValue() - 1) {
                        lastMonth.addProduction(statusDTO.getProduction());
                        lastMonth.addConsumption(statusDTO.getConsumption());
                    } else if (month == LocalDate.now().getMonthValue() - 2) {
                        lastLastMonth.addProduction(statusDTO.getProduction());
                        lastLastMonth.addConsumption(statusDTO.getConsumption());
                    }
                }
                result.addAll(Arrays.asList(lastLastMonth, lastMonth, currentMonth));
                break;
            case MONTH:

            case WEEK:
                break;

        }
        return result;
    }

    private String getMonthDisplayName(Month month) {
        return month.getDisplayName(TextStyle.FULL, Locale.ENGLISH);

    }
}
