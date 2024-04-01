package com.ruoyi.framework.handler;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.function.Supplier;


/**
 * ClassName: MyMetaObjectHandler
 * Package: com.ruoyi.framework.handler
 * Description:
 *
 * @Author yy
 * @Create 2024-04-01 22:40
 * @Version 1.0
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private final static String CREATE_TIME = "createTime";
    private final static String UPDATE_TIME = "updateTime";
//    private final static String LOGIN_TIME = "loginTime";
    private final static String OPER_TIME = "operTime";

    @Override
    public MetaObjectHandler strictFillStrategy(MetaObject metaObject, String fieldName, Supplier<?> fieldVal) {
        Object obj = fieldVal.get();
        if (Objects.nonNull(obj)) {
            metaObject.setValue(fieldName, obj);
        }
        return this;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, CREATE_TIME, DateUtil::date, Date.class);
        this.strictInsertFill(metaObject, OPER_TIME, DateUtil::date, Date.class);
//        this.strictInsertFill(metaObject, LOGIN_TIME, DateUtil::date, Date.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, UPDATE_TIME, DateUtil::date, Date.class);
//        this.strictUpdateFill(metaObject, LOGIN_TIME, DateUtil::date, Date.class);
    }

}
