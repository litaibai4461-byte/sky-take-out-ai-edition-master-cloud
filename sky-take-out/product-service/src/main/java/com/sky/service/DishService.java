package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;
import java.util.Map;

public interface DishService {

    void saveWithFlavor(DishDTO dishDTO);

    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    void deleteBatch(List<Long> ids);

    DishVO getByIdWithFlavor(Long id);

    void update(DishDTO dishDTO);

    List<Dish> list(Long categoryId);

    void startOrStop(Integer status, Long id);

    List<DishVO> listWithFlavor(Dish dish);

    Integer countByMap(Map map);
}
