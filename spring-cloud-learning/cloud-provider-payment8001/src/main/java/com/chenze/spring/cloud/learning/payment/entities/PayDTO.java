package com.chenze.spring.cloud.learning.payment.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -4150209754974269162L;

    private Integer id;

    private String payNo;

    private String orderNo;

    private Integer userId;

    private BigDecimal amount;

}
