package com.chenze.work.bit.test;

import com.chenze.common.util.excel.BaseExcelReadTemplate;
import lombok.Data;

/**
 * @author chenze
 * @date 2021/10/22 15:44
 */
@Data
public class ReadExcelTemplate2 extends BaseExcelReadTemplate {
    private static final long serialVersionUID = 300139627407003381L;

    private String type;

    private String code;

    private String name;


    static {
        headerAlias.put("产品类型","type");
        headerAlias.put("产品编码","code");
        headerAlias.put("产品名称","name");
    }

}
