package com.mapper;

import com.entity.SetmealDish;
import com.vo.DishItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealAndDishMapper {


    /**
     * 根据菜品id查询套餐的数量
     * @param ids
     * @return
     */
    Long selectCountByDishId(List<Long> ids);

    void insertBatch(List<SetmealDish> setmealDishes);

    @Select("select * from setmeal_dish where setmeal_id = #{id}")
    List<SetmealDish> selectById(Long id);

    void deleteBatch(List<Long> ids);

    /**
     * 根据套餐id查询包含的菜品
     * @param id
     * @return
     */
    List<DishItemVO> selectDishById(Long id);
}
