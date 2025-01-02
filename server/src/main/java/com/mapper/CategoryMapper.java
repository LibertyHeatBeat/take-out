package com.mapper;

import com.annotation.AutoFill;
import com.dto.CategoryPageQueryDTO;
import com.entity.Category;
import com.enumeration.OperationType;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 分页查分类
     * @param categoryPageQueryDTO
     * @return
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 新增分类
     * @param category
     * @return
     */
    @Insert("insert into category(type,name,sort,status,create_time,update_time,create_user,update_user) values(#{type},#{name},#{sort},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @AutoFill(OperationType.INSERT)
    void insert(Category category);

    /**
     * 根据类型查分类
     * @param type
     * @return
     */
    List<Category> list(Integer type);

    /**
     * 根据id修改分类
     * @param category
     */
    @AutoFill(OperationType.UPDATE)
    void update(Category category);

    /**
     * 根据id删除分类
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void deleteById(Long id);


    /**
     * 查询所有分类
     * @return
     */
    @Select("select * from category where status = 1")
    List<Category> listAll();
}
