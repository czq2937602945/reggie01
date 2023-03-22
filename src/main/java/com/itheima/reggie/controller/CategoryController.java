package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜品分类管理
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增菜品分类
     * @param category
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Category category){
        categoryService.save(category);
        return R.success("新增分类成功");
    }

    @GetMapping("/page")
    public R<Page> pageCategory(int page,int pageSize){
        log.info("page={},pageSize={}",page,pageSize);
        //分页构造器
        Page<Category> p=new Page<>(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //排序
        queryWrapper.orderByAsc(Category::getSort);
        categoryService.page(p,queryWrapper);
        return R.success(p);
    }

    /**
     * 删除分类
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long ids){
        log.info("删除ID为{}分类",ids);
//        categoryService.removeById(id);

        categoryService.remove(ids);
        return R.success("删除菜品成功");
    }
    /**
     * 修改分类信息
     */
    @PutMapping
    public R<Category> update(@RequestBody Category category){
        log.info("通过ID修改分类信息");
        categoryService.updateById(category);
        return R.success(category);
    }

    /**
     * 页面显示菜品分类下拉信息
     */
    @GetMapping("/list")
    public R<List<Category>> categoryList(Category category){
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(category.getType()!=null,Category::getType,category.getType());
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        List<Category> list = categoryService.list(queryWrapper);

        return R.success(list);
    }
//
//    @GetMapping("/list")
//    public R<List<Category>> categoryList2(Integer integer){
//        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(Category::getType,integer);
//        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
//        List<Category> list = categoryService.list(queryWrapper);
//        return R.success(list);
//    }

}
