package com.service;

import com.dto.DishDTO;
import com.dto.DishPageQueryDTO;
import com.entity.Dish;
import com.result.PageResult;
import com.vo.DishVO;

import java.util.List;

public interface DishService {
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    void saveWithFlovor(DishDTO dishDTO);

    void deleteBatch(List<Long> ids);

    void startOrStop(Integer status, Long id);

    DishVO getByIdWithFlavor(Long id);

    void updateWithFlavor(DishDTO dishDTO);

    List<Dish> getDishByCategoryId(Long categoryId);

    List<DishVO> getDishWithFlavorByCategoryId(Long categoryId);
}
