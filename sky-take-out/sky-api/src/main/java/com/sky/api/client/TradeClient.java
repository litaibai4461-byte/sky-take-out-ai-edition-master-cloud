package com.sky.api.client;
import com.sky.dto.GoodsSalesDTO;
import com.sky.result.Result;
import com.sky.vo.DishVO;
import com.sky.vo.SetmealVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@FeignClient("trade-service")
public interface TradeClient {

    @PutMapping("/user/order/paySuccess")
    void payDirectSuccess(@RequestBody String outTradeNo);

    @PostMapping("/admin/order/sumByMap")
    Double sumByMap(Map map);

    @PostMapping("/admin/order/countByMap")
    Integer countByMap(Map map);

    @PostMapping("/admin/order/getSalesTop")
    List<GoodsSalesDTO> getSalesTop(@RequestBody @RequestParam("beginTime") LocalDateTime begin,@RequestBody @RequestParam("endTime") LocalDateTime end);
}
