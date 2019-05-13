package com.yzpo.crawler.baidutieba.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String [] args ){

        while (true){

            Map<Integer,String> map = BaiduTieBaPageDao.getBaiduTieBaPages();

            if(map.entrySet().size()==0) break;
            for(Map.Entry<Integer,String> e:map.entrySet()) {
                BaiduTiebaPageSpider.getInstance(e.getKey(),e.getValue()).start();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
