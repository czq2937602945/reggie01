package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import org.springframework.web.bind.annotation.PostMapping;

public interface EmployeeService extends IService<Employee> {
    //继承mybatis plus的接口

}
