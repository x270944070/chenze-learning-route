package com.chenze.spring.cloud.learning.payment.controller;

import com.chenze.spring.cloud.learning.payment.entities.Pay;
import com.chenze.spring.cloud.learning.payment.entities.PayDTO;
import com.chenze.spring.cloud.learning.payment.service.PayService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class PayController {

    @Resource
    private PayService payService;

    @PostMapping(value = "/pay/add")
    public String addPay(@RequestBody Pay pay) {
        System.out.println(pay.toString());
        int i = payService.add(pay);
        return "成功插入记录，返回值：" + i;
    }

    @DeleteMapping(value = "/pay/del/{id}")
    public Integer deletePay(@PathVariable("id") Integer id) {
        return payService.delete(id);
    }

    @PutMapping(value = "/pay/update")
    public String updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        int i = payService.update(pay);
        return "成功插入记录，返回值：" + i;
    }

    @GetMapping("/pay/get/{id}")
    public Pay getById(@PathVariable("id")Integer id){
        return payService.getById(id);
    }

    @GetMapping("/pay/getAll")
    public List<Pay> getAll(){
        return payService.getAll();
    }


}
