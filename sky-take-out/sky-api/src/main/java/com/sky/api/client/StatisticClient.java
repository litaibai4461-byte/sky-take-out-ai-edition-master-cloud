package com.sky.api.client;

import com.sky.entity.AddressBook;
import com.sky.entity.Employee;
import com.sky.entity.User;
import com.sky.result.Result;
import com.sky.vo.TurnoverReportVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@FeignClient("statistic-service")
public interface StatisticClient {
    @PostMapping("/admin/report/turnoverStatistics")
    Result<TurnoverReportVO> turnoverStatistics(
            @RequestParam ("startDate")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @RequestParam ("endDate")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end);
}
