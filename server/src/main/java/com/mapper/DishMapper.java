package com.mapper;

import com.annotation.AutoFill;
import com.dto.DishPageQueryDTO;
import com.entity.Dish;
import com.enumeration.OperationType;
import com.vo.DishVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DishMapper {

    /**
     * 分页查菜品
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 添加菜品
     * @param dish
     * @return
     */
    @AutoFill(OperationType.INSERT)
    void insert(Dish dish);

    /**
     * 根据类Id查询菜品数量
     * @param id
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);

    /**
     * 根据类Id批量删除菜品
     * @param ids
     * @return
     */
    void deleteBatch(List<Long> ids);

    /**
     * 更新菜品
     * @param dish
     * @return
     */
    @AutoFill(OperationType.UPDATE)
    void update(Dish dish);

    /**
     * 根据类Id查询菜品
     * @param id
     * @return
     */
    @Select("select * from dish where id = #{id}")
    Dish selectById(Long id);

    /**
     * 根据类Id批量查询菜品售卖状态
     * @param ids
     * @return
     */
    Long selectStatusByDishId(List<Long> ids);

    /**
     * 根据分类Id查询菜品
     *
     * @param categoryId
     * @return
     */
    List<Dish> selectByCategoryId(Long categoryId);


    /**
     * 根据分类id查询菜品和口味
     * @param categoryId
     * @return
     */
    List<DishVO> getDishWithFlavorByCategoryId(Long categoryId);

    Integer countByMap(Map map);
}
