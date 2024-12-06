package com.chenze.common.util.excel;


import com.alibaba.fastjson2.annotation.JSONField;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 不管是read还是write都需要继承这个类
 * @author chenze
 * @create: 2020-05-14 14:58
 */
public abstract class BaseExcelTemplate implements Serializable {

    @Serial
    private static final long serialVersionUID = 1208702987747558825L;

    /**
     * 使用LinkedHashMap，用于在写入excel时列顺序按照实体中字段的顺序
     */
    @JSONField(serialize = false,deserialize = false)
    protected static final Map<String,String> headerAlias = new LinkedHashMap<>();

    public Map<String,String> getHeaderAlias(){
        return headerAlias;
    }


}
