package com.sky.api.client;
import com.sky.result.Result;
import com.sky.vo.DishVO;
import com.sky.vo.SetmealVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient("product-service")
public interface ProductClient {
    @GetMapping("/admin/dish/{id}")
    Result<DishVO> getDishById(@PathVariable Long id);

    @GetMapping("/admin/setmeal/{id}")
    Result<SetmealVO> getSetmealById(@PathVariable Long id);

    @PutMapping("/user/order/paySuccess")
    void payDirectSuccess(@RequestBody String outTradeNo);

    @PostMapping("/admin/dish/countByMap")
    Integer countDishByMap(@RequestBody Map map);

    @PostMapping("/admin/setmeal/countByMap")
    Integer countSetmealByMap(@RequestBody Map map);
}
