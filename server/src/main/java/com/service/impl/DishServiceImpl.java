package com.service.impl;

import com.constant.MessageConstant;
import com.dto.DishDTO;
import com.dto.DishPageQueryDTO;
import com.entity.Dish;
import com.entity.DishFlavor;
import com.exception.DeletionNotAllowedException;
import com.mapper.DishFlavorMapper;
import com.mapper.DishMapper;
import com.mapper.SetmealAndDishMapper;
import com.result.PageResult;
import com.service.DishService;
import com.vo.DishVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Autowired
    private SetmealAndDishMapper setmealAndDishMapper;

    /**
     * 菜品分页查询
     *
     * @param dishPageQueryDTO
     */
    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
        long total = page.getTotal();
        List<DishVO> result = page.getResult();
        return new PageResult(total, result);
    }

    /**
     * 添加菜品和口味
     *
     * @param dishDTO
     */
    @Override
    @Transactional
    public void saveWithFlovor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.insert(dish);
        Long dishId=dish.getId();
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors!=null && flavors.size()>0){
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishId);
            });
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    /**
     * 菜品批量删除
     *
     * @param ids
     */
    @Override
    @Transactional
    public void deleteBatch(List<Long> ids) {
        Long count=setmealAndDishMapper.selectCountByDishId(ids);
        if (count>0){
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }
        count = dishMapper.selectStatusByDishId(ids);
        if (count>0){
            throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
        }
        dishMapper.deleteBatch(ids);
        dishFlavorMapper.deleteBatch(ids);
    }

    /**
     * 菜品启用和停用
     *
     * @param status
     * @param id
     */
    @Override
    public void startOrStop(Integer status, Long id) {
        Dish dish = Dish.builder().id(id).status(status).build();
        dishMapper.update(dish);
    }

    /**
     * 通过Id获得菜品和口味
     *
     * @param id
     */
    @Override
    public DishVO getByIdWithFlavor(Long id) {
        DishVO dishVO= new DishVO();
        Dish dish = dishMapper.selectById(id);
        List<DishFlavor> dishFlavors = dishFlavorMapper.selectByDishId(id);
        dishVO.setFlavors(dishFlavors);
        BeanUtils.copyProperties(dish,dishVO);
        return dishVO;
    }

    /**
     * 更新菜品和口味
     *
     * @param dishDTO
     */
    @Override
    @Transactional
    public void updateWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dishMapper.update(dish);
        List<Long> id = new ArrayList<>();
        id.add(dishDTO.getId());
        dishFlavorMapper.deleteBatch(id);
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors!=null && flavors.size()>0){
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dish.getId());
            });
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    /**
     * 根据分类Id查询菜品
     *
     * @param categoryId
     * @return
     */
    @Override
    public List<Dish> getDishByCategoryId(Long categoryId) {
        List<Dish> list = dishMapper.selectByCategoryId(categoryId);
        return list;
    }

    /**
     * 根据分类id查询菜品和口味
     * @param categoryId
     * @return
     */
    @Override
    public List<DishVO> getDishWithFlavorByCategoryId(Long categoryId) {

        List<DishVO> list = dishMapper.getDishWithFlavorByCategoryId(categoryId);
        for (DishVO dishVO : list){
            dishVO.setFlavors(dishFlavorMapper.selectByDishId(dishVO.getId()));
        }
        return list;
    }
}
