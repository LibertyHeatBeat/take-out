package com.service;

import com.dto.SetmealDTO;
import com.dto.SetmealPageQueryDTO;
import com.result.PageResult;
import com.vo.DishItemVO;
import com.vo.SetmealVO;

import java.util.List;

public interface SetmealService {
    void save(SetmealDTO setmealDTO);

    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    SetmealVO getSetmealById(Long id);

    void update(SetmealDTO setmealDTO);

    void startOrStop(Integer status, Long id);

    void deleteBatch(List<Long> ids);

    List<SetmealVO> getSetmealsByCategoryId(Long categoryId);

    List<DishItemVO> getDishBySetmealId(Long id);
}
