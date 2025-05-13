package com.chenze.learning.persontool.test;

import com.chenze.learning.persontool.ObsidianService;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ObsidianServiceTest {

    private ObsidianService obsidianService;

    @Before
    public void init(){
        obsidianService = new ObsidianService();
    }

    @Test
    public void test1() throws UnsupportedEncodingException {
        String s = obsidianService.dirToMindmap("C:/Users/chenze/Documents/obsidian/", "知识积累/MySQL");

        String mm = "---\n" +
                "mindmap-plugin: basic\n" +
                "tags:\n" +
                "  - 知识体系\n" +
                "  - 思维导图\n" +
                "title: MySQL\n" +
                "---\n\n";


        System.out.println(mm + s);

    }

}
