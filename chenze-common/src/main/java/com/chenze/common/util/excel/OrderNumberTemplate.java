package com.chenze.common.util.excel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author chenze
 * @date 2021/10/27 17:56
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderNumberTemplate extends BaseExcelReadTemplate {

    private String orderNumber;

    static {
        headerAlias.put("orderNumber", "orderNumber");
    }
}
