package com.yzpo.crawler.baidutieba.titles;

import com.yzpo.crawler.baidutieba.titles.BaiduTiebaTitlePipeline;
import com.yzpo.crawler.baidutieba.titles.BaiduTiebaTitleProcessor;
import us.codecraft.webmagic.Spider;

public class BaiduTiebaTitleSpider {

    private static Spider aspider = Spider.create(new BaiduTiebaTitleProcessor())
            .addPipeline(new BaiduTiebaTitlePipeline())
            .addUrl("https://tieba.baidu.com/f?ie=utf-8&kw=%E9%82%AE%E6%94%BF&fr=search")
                .thread(Runtime.getRuntime().availableProcessors());

    public static Spider getInstance() {
        return aspider;
    }

}
