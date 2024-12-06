package com.chenze.work.bits.service;

import cn.hutool.core.util.RandomUtil;
import com.chenze.work.bits.dao.LongtransactionFromMapper;
import com.chenze.work.bits.dao.LongtransactionToMapper;
import com.chenze.work.bits.model.entity.LongtransactionFrom;
import com.chenze.work.bits.model.entity.LongtransactionTo;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MainService {

    @Resource
    private LongtransactionFromMapper longtransactionFromMapper;
    @Resource
    private LongtransactionToMapper longtransactionToMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    public LongtransactionFrom findOne(String userCode){
        return longtransactionFromMapper.selectByPrimaryKey(userCode);
    }

    @Transactional
    public void longtransactionExe() throws InterruptedException {

        long finishTime = Instant.now().toEpochMilli() + 1000*60*30;
        String maxUserCode = null;
        LongtransactionTo fixed = new LongtransactionTo();
        while(true) {

            fixed.setUserCode(maxUserCode);

            int start = RandomUtil.randomInt(0, 500);
            List<LongtransactionFrom> froms = longtransactionFromMapper.selectLimit(start, 20);

            List<LongtransactionTo> toList = new ArrayList<>();
            for (LongtransactionFrom from : froms) {
                LongtransactionTo to = new LongtransactionTo();
                BeanUtils.copyProperties(from, to);
                toList.add(to);
            }

            longtransactionToMapper.batchInsert(toList);
            mongoTemplate.insertAll(toList);


            maxUserCode = toList.stream()
                    .max(Comparator.comparing(LongtransactionTo::getUserCode))
                    .map(LongtransactionTo::getUserCode).orElse(null);

            Thread.sleep(100);

            long currentTime = Instant.now().toEpochMilli();
            if (currentTime > finishTime) {
                break;
            }
        }



    }


}
