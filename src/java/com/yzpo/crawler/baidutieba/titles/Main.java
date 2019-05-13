package com.yzpo.crawler.baidutieba.titles;

public class Main {
    public static void main(String [] args) {

        System.out.println(Runtime.getRuntime().availableProcessors()*8);
        BaiduTiebaTitleSpider.getInstance().start();
    }
}
