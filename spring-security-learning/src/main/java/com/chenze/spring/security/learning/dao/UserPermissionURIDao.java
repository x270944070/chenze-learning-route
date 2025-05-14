package com.chenze.spring.security.learning.dao;

import com.chenze.spring.security.learning.model.entiry.UserInfo;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserPermissionURIDao {


    private Map<String, List<String>> dataMap;

    @PostConstruct
    public void init() {
        dataMap = new HashMap<>();
        dataMap.put("ROLE_admin", new ArrayList<String>() {{
            add("main4");
            add("main5");
            add("main6");
        }});
        dataMap.put("normal", new ArrayList<String>() {{
            add("main6");
        }});
    }


    public List<String> selectOne(String role) {
        return dataMap.get(role);
    }

}
