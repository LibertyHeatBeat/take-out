package com.controller.user;

import com.entity.Category;
import com.result.Result;
import com.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类管理
 */
@RestController("userCategoryController")
@RequestMapping("/user/category")
@Slf4j
@Api(tags = "分类相关接口")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 查询所有分类
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询所有分类")
    public Result<List<Category>> list(){
       List<Category> categoryList = categoryService.listAll();
       return Result.success(categoryList);
    }

}
