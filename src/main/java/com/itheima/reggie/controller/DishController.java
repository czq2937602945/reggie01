package com.itheima.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.DishFlavor;
import com.itheima.reggie.service.CategoryService;
import com.itheima.reggie.service.DishFlavorService;
import com.itheima.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增菜品，包含菜品表，口味表操作
     * @param dishDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){
        log.info(String.valueOf(dishDto));
        dishService.saveWithFlavor(dishDto);
        return R.success("新增菜品成功");
    }

    /**
     * 显示菜品页面
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> getDishPage(int page,int pageSize,String name){

        Page<Dish> pageInfo=new Page<>(page,pageSize);
        Page<DishDto> dtoPage = new Page<>();
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        if(name!= null){
            //条件构造器
            queryWrapper.like(Dish::getName,name);
        }
        queryWrapper.orderByDesc(Dish::getUpdateTime);
        dishService.page(pageInfo,queryWrapper);
        //对象拷贝
        BeanUtils.copyProperties(pageInfo,dtoPage,"records");
        List<Dish> records = pageInfo.getRecords();
        List<DishDto> dtoList = records.stream().map((item) -> {
            DishDto dishDto = new DishDto();
            //将item所对应Dish对象传给DishDto
            BeanUtils.copyProperties(item, dishDto);
            //根据分类ID查询分类的对象
            Long categoryId = item.getCategoryId();
            Category categorybyId = categoryService.getById(categoryId);
            //存在菜品没有分类，避免空指针
            if (categorybyId!=null){
                //设置categoryName
                dishDto.setCategoryName(categorybyId.getName());
            }
            return dishDto;
        }).collect(Collectors.toList());
        dtoPage.setRecords(dtoList);
        return R.success(dtoPage);
    }

    @GetMapping("/{id}")
    public R<DishDto> update(@PathVariable Long id){
        //获取DishDto对象
        DishDto dishDto = new DishDto();
        //通过前端获取的ID的到对应的Dish
        Dish byId = dishService.getById(id);
        //通过工具将dish属性赋给dishDto
        BeanUtils.copyProperties(byId,dishDto);
//        LambdaQueryWrapper<Category> queryWrapper1 = new LambdaQueryWrapper<>();
//        queryWrapper1.eq(Category::getId,byId.getCategoryId());

        //填充dishDto对象属性
        Category category = categoryService.getById(byId.getCategoryId());
        dishDto.setCategoryName(category.getName());

        LambdaQueryWrapper<DishFlavor> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(DishFlavor::getDishId,byId.getId());

        List<DishFlavor> list = dishFlavorService.list(queryWrapper2);
        //填充flavors口味的属性
        dishDto.setFlavors(list);
        return R.success(dishDto);
    }

    @PutMapping
    public R<String> revise(@RequestBody DishDto dishDto){
        dishService.reviseWishFlavor(dishDto);
        return R.success("修改成功");
        //ceshi
    }



    @GetMapping("/list")
    public R<List<Dish>> listByDishCategoryId(Dish dish){
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(dish.getCategoryId()!=null,Dish::getCategoryId,dish.getCategoryId());
        queryWrapper.orderByAsc(Dish::getSort);
        List<Dish> list = dishService.list(queryWrapper);
        return R.success(list);
    }

    @DeleteMapping
    private R<String> deleteByIds(@RequestParam Long ids){
        log.info("***********{}",ids);
        return R.success("删除菜品成功");
    }

}
