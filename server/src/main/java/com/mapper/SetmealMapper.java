package com.mapper;

import com.annotation.AutoFill;
import com.dto.SetmealPageQueryDTO;
import com.entity.Setmeal;
import com.enumeration.OperationType;
import com.vo.SetmealVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SetmealMapper {

    /**
     * 根据分类id查询套餐的数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    @AutoFill(OperationType.INSERT)
    void insert(Setmeal setmeal);

    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    @Select("select * from setmeal where id = #{id}")
    Setmeal selectById(Long id);

    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);

    Long selectCountByIds(List<Long> ids);

    void deleteBatch(List<Long> ids);

    /**
     * 根据分类id查询套餐
     * @param categoryId
     * @return
     */
    @Select("select * from setmeal where category_id = #{categoryId} and status = 1")
    List<SetmealVO> selectByCategoryId(Long categoryId);

    Integer countByMap(Map map);
}
