package com.controller.user;

import com.result.Result;
import com.service.SetmealService;
import com.vo.DishItemVO;
import com.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("userSetmealController")
@RequestMapping("/user/setmeal")
@Slf4j
@Api(tags = "套餐相关接口")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;


    /**
     * 根据分类id查询套餐
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "根据分类id查询套餐")
    @Cacheable(cacheNames = "setmealCache", key = "#categoryId")
    public Result<List<SetmealVO>> selectById(@RequestParam("categoryId") Long categoryId){
        log.info("根据分类id查询套餐：{}", categoryId);
        List<SetmealVO> setmealVO = setmealService.getSetmealsByCategoryId(categoryId);
        return Result.success(setmealVO);
    }

    /**
     * 根据套餐id查询包含的菜品
     * @param id
     * @return
     */
    @GetMapping("/dish/{id}")
    @ApiOperation(value = "根据套餐id查询包含的菜品")
    @Cacheable(cacheNames = "setmealCache", key = "'dish'+#id")
    public Result<List<DishItemVO>> selectDishById(@PathVariable Long id){
        log.info("根据套餐id查询包含的菜品：{}", id);
        List<DishItemVO> dishItemVOS = setmealService.getDishBySetmealId(id);
        return Result.success(dishItemVOS);
    }

}
