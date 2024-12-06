package com.chenze.common.util.excel;

import cn.hutool.poi.excel.cell.CellEditor;

import java.io.Serial;

/**
 * @author chenze
 * @create: 2020-06-04 15:43
 */
public class BaseExcelReadTemplate extends BaseExcelTemplate {
    @Serial
    private static final long serialVersionUID = 3870087255889368020L;


    public CellEditor cellEditor(){
        return (cell, value) -> value;
    }

}
