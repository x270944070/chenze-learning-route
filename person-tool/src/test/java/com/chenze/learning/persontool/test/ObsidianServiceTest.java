package com.chenze.learning.persontool.test;

import com.chenze.learning.persontool.ObsidianService;
import org.junit.Before;
import org.junit.Test;

public class ObsidianServiceTest {

    private ObsidianService obsidianService;

    @Before
    public void init(){
        obsidianService = new ObsidianService();
    }

    @Test
    public void test1(){
        String s = obsidianService.dirToMindmap("C:\\Users\\chenze\\Documents\\obsidian\\", "知识积累\\MySQL");
        System.out.println(s);

    }

}
