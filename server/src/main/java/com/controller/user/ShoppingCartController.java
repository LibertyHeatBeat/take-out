package com.controller.user;

import com.dto.ShoppingCartDTO;
import com.entity.ShoppingCart;
import com.result.Result;
import com.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车
 */
@RestController("userShoppingCartController")
@RequestMapping("/user/shoppingCart")
@Slf4j
@Api(tags = "C端购物车相关接口")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 购物车添加菜品和套餐
     *
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "购物车添加菜品和套餐")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("购物车添加菜品和套餐:{}",shoppingCartDTO);
        shoppingCartService.add(shoppingCartDTO);
        return Result.success();
    }

    /**
     * 购物车查询菜品和套餐
     *
     * @param
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "购物车查询菜品和套餐")
    public Result<List<ShoppingCart>> list(){
        List<ShoppingCart> shoppingCarts = shoppingCartService.list();
        return Result.success(shoppingCarts);
    }


    /**
     * 购物车清空
     *
     * @param
     * @return
     */
    @DeleteMapping("/clean")
    @ApiOperation(value = "购物车清空")
    public Result clean(){
        shoppingCartService.clean();
        return Result.success();
    }

    /**
     * 购物车菜品和套餐数量减一
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/sub")
    public Result sub(@RequestBody ShoppingCartDTO shoppingCartDTO){
        shoppingCartService.sub(shoppingCartDTO);
        return Result.success();
    }

}
