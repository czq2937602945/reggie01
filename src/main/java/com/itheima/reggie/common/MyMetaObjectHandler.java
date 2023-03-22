package com.itheima.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
//    @Autowired
//    public HttpServletRequest request;
    @Override
    public void insertFill(MetaObject metaObject) {

        /**
         * 在此处对于公共字段的处理会迟于在在之前的save方法中的设置，会对代码值覆盖
         */
        log.info("插入字段填充，，，，，，，");
        log.info(metaObject.toString());
        LocalDateTime l1=LocalDateTime.now();
        log.info("在metaObject中的时间是{}",l1);
        metaObject.setValue("createTime", l1);
        metaObject.setValue("updateTime",LocalDateTime.now());
//        metaObject.setValue("createUser",(Long)request.getSession().getAttribute("employee"));
//        metaObject.setValue("updateUser",(Long)request.getSession().getAttribute("employee"));
        metaObject.setValue("createUser",BaseContext.getCurrentId());
        metaObject.setValue("updateUser",BaseContext.getCurrentId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新字段填充，，，，，，，");
        log.info(metaObject.toString());
        metaObject.setValue("updateTime",LocalDateTime.now());
        metaObject.setValue("updateUser",BaseContext.getCurrentId());

    }
}
